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
    
    private int index(int[] index) {
        int access = 0;
        for (int i = shape.length; i > 0; i--) {
            access *= shape[i - 1];
            access += index[shape.length - i];
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
        
        int tensorSize = 1;
        int distanceBetween = 1;
        for (int i = 0; i < shape.length; i++)
            if (i != axis)
                tensorSize *= shape[i];
        for (int i = axis + 1; i < shape.length; i++) {
            distanceBetween *= shape[i];
        }
        
        for (int i = 0; i < shape[axis]; i++) {
            APLTensor t = new APLTensor(tensorSize);
            for (int j = 0; j < tensorSize; j++) {
                if (axis == 0) {
                    t.set(values[i*tensorSize + j], j);
                } else {
                    t.set(values[i + j*shape[axis]], j);
                }
            }
            ret[i] = t;
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
        
        int[] rightpad = new int[shape[shape.length-1]];
        int[] leftpad = new int[shape[shape.length-1]];
        for (int i = 0; i < values.length; i++) {
            int j = i % shape[shape.length-1];
            rightpad[j] = Math.max(rightpad[j], strValues[i].length() + 1);
            leftpad[j] = strValues[i].charAt(0) == '-' ? 1 : leftpad[j];
        }
        
        for (int i = 0; i < values.length; i++) {
            int sh = 1;
            for (int j = shape.length-1; j >= 0; j--) {
                sh *= shape[j];
                if (((int)(i / sh) >= 1) && (i % sh == 0))
                    str += "\n";
            }
            int j = i % shape[shape.length-1];
            
            String form = "%"+(-rightpad[j])+"s";
            if (leftpad[j] > 0 && strValues[i].charAt(0) != '-')
                strValues[i] = " " + strValues[i];
            str += String.format(form, strValues[i]);
        }
        
        // Print underline as separator
        //str += "\n"+ (char)27 + "[4m" + String.format("%80s","") + (char)27 + "[24m";
        
        return str;
    }
}