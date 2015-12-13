import java.util.*;
import java.text.DecimalFormat;

public class APLTensor {
    private int[] shape;
    private double[] values;
    
    public APLTensor(int... shape) {
        this.shape = shape;
        this.values = new double[lengthFromShape()];
        Arrays.fill(values,0);
    }
    
    public APLTensor(double... values) {
        this.shape = new int[] {values.length};
        this.values = values;
    }
    
    public APLTensor(double[] values, int... shape) {
        this.shape = shape;
        this.values = values;
    }
    
    private int lengthFromShape() {
        int l = 1;
        for (int i = 0; i < shape.length; i++)
            l *= shape[i];
        return l;
    }
    
    public static int lengthFromShape(int[] shape) {
        int l = 1;
        for (int i = 0; i < shape.length; i++)
            l *= shape[i];
        return l;
    }
    
    public int index(int[] index) {
        int access = 0;
        int sh = values.length;
        for (int i = 0; i < shape.length; i++) {
            sh /= shape[i];
            access += sh * index[i];
        }
        return access;
    }
    
    public int[] index(int index) {
        int access[] = new int[shape.length];
        int sh = values.length;
        int runningIndex = index;
        for (int i = 0; i < shape.length; i++) {
            sh /= shape[i];
            int idx = runningIndex / sh;
            runningIndex %= sh;
            access[i] = idx;
        }
        
        return access;
    }
    
    public double get(int... index) {
        if (index.length == 1)
            return values[index[0]];
        else if (index.length == shape.length)
            return values[index(index)];
        else
            return -1;
    }
    
    public void set(double value, int... index) {
        if (index.length == 1)
            values[index[0]] = value;
        else if (index.length == shape.length)
            values[index(index)] = value;
    }
    
    public APLTensor shapeAsTensor() {
        double s[] = new double[shape.length];
        for (int i = 0; i < shape.length; i++) s[i] = shape[i];
        return new APLTensor(s);
    }
    
    public void reshape(int... s) {
        int oldlength = values.length;
        this.shape = s;
        int newlength = lengthFromShape();
        double[] newvalues = new double[newlength];
        for (int i = 0; i < newlength; i++)
            newvalues[i] = values[i % oldlength];
        this.values = newvalues;
    }
    
    public void reshape(double... s) {
        int[] ns = new int[s.length];
        for (int i = 0; i < s.length; i++)
            ns[i] = (int)s[i];
        reshape(ns);
    }
    
    public void restrict(int... s) {
        int[] oldshape = shape;
        int[] newshape = new int[s.length];
        int oldlength = lengthFromShape();
        int newlength = 1;
        for (int i = 0; i < shape.length; i++) {
            newshape[i] = Math.abs(s[i]);
            newlength *= newshape[i];
        }
        
        double[] newvalues = new double[newlength];
        
        int oldindex = 0;
        int newindex = 0;
        
        while (oldindex < oldlength && newindex < newlength) {
            boolean outsideNew = false;
            boolean outsideOld = false;
            int os = 1;
            int ns = 1;
            for (int i = newshape.length-1; i >= 0; i--) {
                os *= oldshape[i];
                ns *= newshape[i];
                
                if (s[i] > 0) {
                    // If the index in the old dimension is greater than the
                    //  index in the new dimension, we are outside the new one
                    if (oldindex / os > newindex / ns)
                        outsideNew = true;
                    // If the index in the new dimension is greater than the
                    //  index in the old dimension, we are outside the old one
                    if (oldindex / os < newindex / ns)
                        outsideOld = true;
                } else {
                    // If the index in the old dimension is greater than the
                    //  index in the new dimension, we are outside the new one
                    if ((oldindex - (oldshape[i]*os - newshape[i]*ns)) / os > newindex / ns)
                        outsideNew = true;
                    // If the index in the new dimension is greater than the
                    //  index in the old dimension, we are outside the old one
                    if ((oldindex - (oldshape[i]*os - newshape[i]*ns)) / os < newindex / ns)
                        outsideOld = true;
                }
            }
            if (outsideNew) {
                newindex++;
            } else if (outsideOld) {
                oldindex++;
            } else {
                newvalues[newindex++] = values[oldindex++];
            }
        }
        
        values = newvalues;
        shape = newshape;
    }
    
    public int length() {
        return values.length;
    }
    
    public APLTensor clone() {
        return new APLTensor(values, shape);
    }
    
    public double[] values() {
        return values.clone();
    }
    
    public int[] shape() {
        return shape.clone();
    }
    
    public int dimensions() {
        return shape.length;
    }
    
    public APLTensor[] alongAxis(int axis) {
        APLTensor[] ret = new APLTensor[shape[axis]];
        int[] newshape = new int[shape.length - 1];
        
        int tensorSize = 1;
        for (int i = 0; i < axis; i++) {
            tensorSize *= shape[i];
            newshape[i] = shape[i];
        }
        for (int i = axis+1; i < shape.length; i++) {
            tensorSize *= shape[i];
            newshape[i-1] = shape[i];
        }
        
        for (int i = 0; i < shape[axis]; i++) {
            APLTensor t = new APLTensor(newshape);
            for (int j = 0; j < tensorSize; j++) {
                if (axis == 0)
                    t.set(values[i*tensorSize + j], j);
                else
                    t.set(values[i + j*shape[axis]], j);
            }
            ret[i] = t;
        }
        return ret;
    }
    
    public static APLTensor mergeAxes(int axis, APLTensor[] axes) {
        int[] oldshape = axes[0].shape();
        int[] newshape = new int[oldshape.length + 1];
        for (int i = 0; i < axis; i++)
            newshape[i] = oldshape[i];
        newshape[axis] = axes.length;
        for (int i = axis; i < oldshape.length; i++)
            newshape[i+1] = oldshape[i];
        
        int tensorSize = axes[0].length();
        
        APLTensor ret = new APLTensor(newshape);
        
        for (int i = 0; i < axes.length; i++) {
            for (int j = 0; j < tensorSize; j++) {
                if (axis == 0)
                    ret.set(axes[i].get(j), i*tensorSize + j);
                else
                    ret.set(axes[i].get(j), i + j*newshape[axis]);
            }
        }
        return ret;
    }
    
    public boolean equals(APLTensor other) {
        return Arrays.equals(values, other.values()) && Arrays.equals(shape, other.shape);
    }
    
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##########");
        String str = "";
        
        
        String[] strValues = new String[values.length];
        for (int i = 0; i < values.length; i++)
            strValues[i] = df.format(values[i]);
        
        int[] columnLeft = new int[shape[shape.length - 1]];
        int[] columnRight = new int[shape[shape.length - 1]];
        for (int i = 0; i < values.length; i++) {
            int j = i % shape[shape.length-1];
            int decimal = strValues[i].indexOf('.');
            if (decimal < 0) {
                decimal = strValues[i].length();
                strValues[i] = strValues[i] + " ";
            }
            int before = decimal;
            int after = strValues[i].length() - decimal - 1;
            columnLeft[j] = Math.max(columnLeft[j], before);
            columnRight[j] = Math.max(columnRight[j], after);
        }
        
        for (int i = 0; i < values.length; i++) {
            String v = strValues[i];
            int j = i % shape[shape.length-1];
            
            int decimal = v.indexOf('.');
            if (decimal < 0) {
                decimal = v.length() - 1;
            }
            int before = decimal;
            int leftDecimalPad = v.length() - before + columnLeft[j];
            String form = "%"+(leftDecimalPad)+"s";
            strValues[i] = String.format(form, strValues[i]);
            
            int after = v.length() - decimal - 1;
            int rightDecimalPad = strValues[i].length() - after + columnRight[j];
            form = "%"+(-rightDecimalPad)+"s";
            strValues[i] = String.format(form, strValues[i]) + " ";
            
        }
        
        for (int i = 0; i < values.length; i++) {
            int sh = 1;
            for (int j = shape.length-1; j >= 0; j--) {
                sh *= shape[j];
                if (((int)(i / sh) >= 1) && (i % sh == 0))
                    str += "\n";
            }
            //int j = i % shape[shape.length-1];
            
            str += strValues[i];
        }
        
        return str;
    }
}