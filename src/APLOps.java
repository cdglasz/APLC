import java.util.*;
import java.text.DecimalFormat;
public class APLOps {
    
    private static String errorLog = "";
    
    public static void log(String error) {
        errorLog += error + "\n";
    }
    
    public static void flush() {
        System.out.print(errorLog);
        errorLog = "";
    }
    
    static abstract class Operation {
        public abstract String symbol();
        public abstract APLTensor exec(APLTensor left, APLTensor right);
    }
    public static APLTensor exec(Operation op) {
        return op.exec(null, null);
    }
    
    public static APLTensor exec(Operation op, APLTensor right) {
        if (right == null) {
            log("NULL ARGUMENT TO MONADIC OPERATOR " + op.symbol());
            return null;
        }
        return op.exec(null, right);
    }
    
    public static APLTensor exec(Operation op, APLTensor left, APLTensor right) {
        if (right == null) {
            log("NULL RIGHT ARGUMENT TO DYADIC OPERATOR " + op.symbol());
            return null;
        }
        if (left == null) {
            log("NULL LEFT ARGUMENT TO DYADIC OPERATOR " + op.symbol());
            return null;
        }
        return op.exec(left, right);
    }
    
    //========================================//
    //           Adverb Operators             //
    //========================================//
    static class reduce extends Operation {
        public String symbol() { return op.symbol() + "/"; }
        private Operation op;
        
        public reduce(Operation op) {
            this.op = op;
        }
        
        public APLTensor exec(APLTensor dc, APLTensor a) {
            if (a.dimensions() == 1) {
                APLTensor running = new APLTensor(a.get(0));
                for (int i = 1; i < a.length(); i++)
                    running = op.exec(running, new APLTensor(a.get(i)));
                return running;
            }
            
            APLTensor[] axes = a.alongAxis(a.dimensions()-1);
            APLTensor running = axes[0].clone();
            for (int i = 1; i < axes.length; i++) {
                running = op.exec(running, axes[i]);
            }
            return running;
        }
    }
    
    static class reduce1 extends Operation {
        public String symbol() { return op.symbol() + "⌿"; }
        private Operation op;
        
        public reduce1(Operation op) {
            this.op = op;
        }
        
        public APLTensor exec(APLTensor dc, APLTensor a) {
            if (a.dimensions() == 1) {
                APLTensor running = new APLTensor(a.get(0));
                for (int i = 1; i < a.length(); i++)
                    running = op.exec(running, new APLTensor(a.get(i)));
                return running;
            }
            
            APLTensor[] axes = a.alongAxis(0);
            APLTensor running = axes[0].clone();
            for (int i = 1; i < axes.length; i++) {
                running = op.exec(running, axes[i]);
            }
            return running;
        }
    }
    
    static class scan extends Operation {
        public String symbol() { return op.symbol() + "\\"; }
        private Operation op;
        
        public scan(Operation op) {
            this.op = op;
        }
        
        public APLTensor exec(APLTensor dc, APLTensor a) {
            if (a.dimensions() == 1) {
                APLTensor running = new APLTensor(a.shape());
                running.set(a.get(0),0);
                for (int i = 1; i < a.length(); i++)
                    running.set(op.exec(new APLTensor(running.get(i-1)),
                                        new APLTensor(a.get(i))).get(0), i);
                return running;
            }
            APLTensor[] axes = a.alongAxis(a.dimensions()-1);
            APLTensor[] running = new APLTensor[axes.length];
            running[0] = axes[0].clone();
            for (int i = 1; i < axes.length; i++) {
                running[i] = op.exec(running[i-1], axes[i]);
            }
            return APLTensor.mergeAxes(a.dimensions()-1, running);
        }
    }
    
    static class scan1 extends Operation {
        public String symbol() { return op.symbol() + "⍀"; }
        private Operation op;
        
        public scan1(Operation op) {
            this.op = op;
        }
        
        public APLTensor exec(APLTensor dc, APLTensor a) {
            if (a.dimensions() == 1) {
                APLTensor running = new APLTensor(a.shape());
                running.set(a.get(0),0);
                for (int i = 1; i < a.length(); i++)
                    running.set(op.exec(new APLTensor(running.get(i-1)),
                                        new APLTensor(a.get(i))).get(0), i);
                return running;
            }
            APLTensor[] axes = a.alongAxis(0);
            APLTensor[] running = new APLTensor[axes.length];
            running[0] = axes[0].clone();
            for (int i = 1; i < axes.length; i++) {
                running[i] = op.exec(running[i-1], axes[i]);
            }
            return APLTensor.mergeAxes(0, running);
        }
    }
    
    static class each extends Operation {
        public String symbol() { return op.symbol() + "¨"; }
        private Operation op;
        
        public each(Operation op) {
            this.op = op;
        }
        
        public APLTensor exec(APLTensor dc, APLTensor a) {
            int l = a.length();
            double[] c = new double[l];
            for (int i = 0; i < l; i++)
                c[i] = op.exec(null, new APLTensor(a.get(i))).get(0);
            return new APLTensor(c, a.shape());
        }
    }
    
    //========================================//
    //          Conjugate Operators           //
    //========================================//
    static class tie extends Operation {
        public String symbol() { return op1.symbol() + "." + op2.symbol(); }
        private Operation op1;
        private Operation op2;
        
        private Operation ac;
        
        public tie(Operation op1, Operation op2) {
            this.op1 = op1;
            this.op2 = op2;
            
            this.ac = new reduce(op1);
        }
        
        public APLTensor exec(APLTensor a, APLTensor b) {
            // The inner product of 2 vectors is a dot product
            if (a.dimensions() == 1 && b.dimensions() == 1)
                return dot(a, b);
            
            // Anything more than matrix products is really hard to implement
            if (a.dimensions() > 2 || b.dimensions() > 2) {
                log("OPERATOR " + symbol() + " NOT SUPPORTED FOR TENSORS OF >2 DIMENSIONS");
                return null;
            }
            
            // Inner dimensions much match
            if (a.shape()[a.dimensions()-1] != b.shape()[0]) {
                log("LENGTH ERROR AT OPERATOR " + symbol());
                return null;
            }
            
            int[] shape;
            APLTensor[] aa;
            APLTensor[] bb;
            if (a.dimensions() == 1) {
                shape = new int[b.dimensions()];
                shape[0] = 1;
                for (int i = 1; i < b.dimensions(); i++)
                    shape[i] = b.shape()[i];
                
                aa = new APLTensor[]{a};
                bb = b.alongAxis(b.dimensions() - 1);
            } else if (b.dimensions() == 1) {
                shape = new int[a.dimensions()];
                for (int i = 0; i < a.dimensions() - 1; i++)
                    shape[i] = a.shape()[i];
                shape[shape.length-1] = 1;
                
                aa = a.alongAxis(0);
                bb = new APLTensor[]{b};
            } else {
                shape = new int[a.dimensions() + b.dimensions() - 2];
                for (int i = 0; i < a.dimensions() - 1; i++)
                    shape[i] = a.shape()[i];
                for (int i = 1; i < b.dimensions(); i++)
                    shape[i + a.dimensions() - 2] = b.shape()[i];
                
                aa = a.alongAxis(0);
                bb = b.alongAxis(b.dimensions() - 1);
            }
            
            double[] c = new double[APLTensor.lengthFromShape(shape)];
            int idx = 0;
            for (int i = 0; i < aa.length; i++) {
                for (int j = 0; j < bb.length; j++) {
                    c[idx++] = dot(aa[i], bb[j]).get(0);
                }
            }
            
            return new APLTensor(c, shape);
        }
        
        public APLTensor dot(APLTensor a, APLTensor b) {
            
            APLTensor r = op2.exec(a, b);
            return ac.exec(null, r);
            
        }
    }
    
    static class null_tie extends Operation {
        public String symbol() { return "∘." + op.symbol(); }
        private Operation op;
        
        public null_tie(Operation op) {
            this.op = op;
        }
        
        public APLTensor exec(APLTensor a, APLTensor b) {
            
            int[] shape = new int[a.dimensions() + b.dimensions()];
            for (int i = 0; i < a.dimensions(); i++)
                shape[i] = a.shape()[i];
            for (int i = 0; i < b.dimensions(); i++)
                shape[i+a.dimensions()] = b.shape()[i];
            
            double[] c = new double[APLTensor.lengthFromShape(shape)];
            
            int idx = 0;
            for (int i = 0; i < a.length(); i++) {
                for (int j = 0; j < b.length(); j++) {
                    APLTensor r = APLOps.exec(op, new APLTensor(a.get(i)),
                                              new APLTensor(b.get(j)));
                    if (r == null) {
                        return null;
                    }
                    c[idx++] = r.get(0);
                }
            }
            
            return new APLTensor(c, shape);
        }
    }
    
    //========================================//
    //           Monadic Operators            //
    //========================================//
    
    // Monadic function associated with +
    static class clone extends Operation {
        public String symbol() { return "+"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.clone(a);
        }
    }
    public static APLTensor clone(APLTensor a) {
        return a.clone();
    }
    
    // Monadic function associated with ~
    static class not extends Operation {
        public String symbol() { return "~"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.not(a);
        }
    }
    public static APLTensor not(APLTensor a) {
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = c[i] == 0 ? 1 : 0;
        return new APLTensor(c,a.shape());
    }
    
    // Monadic function associated with ⍋
    static class sort extends Operation {
        public String symbol() { return "⍋"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.sort(a);
        }
    }
    public static APLTensor sort(APLTensor a) {
        if (a.dimensions() == 1) {
            double[] c = a.values();
            Arrays.sort(c);
            return new APLTensor(c,a.shape());
        }
        APLTensor[] axes = a.alongAxis(0);
        for (int i = 0; i < axes.length; i++)
            axes[i] = sort(axes[i]);
        return APLTensor.mergeAxes(0, axes);
    }
    
    // Monadic function associated with ⍒
    static class sortdown extends Operation {
        public String symbol() { return "⍒"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.sortdown(a);
        }
    }
    public static APLTensor sortdown(APLTensor a) {
        return reverse(sort(a));
    }
    
    // Monadic function associated with ?
    static class roll extends Operation {
        public String symbol() { return "?"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.roll(a);
        }
    }
    public static APLTensor roll(APLTensor a) {
        Random rand = new Random();
        double[] c = a.values();
        for (int i = 0; i < c.length; i++) {
            if (c[i] <= 0) {
                log("DOMAIN ERROR AT OPERATOR ?");
                return null;
            }
            c[i] = rand.nextInt((int)c[i]) + 1;
        }
        return new APLTensor(c, a.shape());
    }
    
    // Monadic function associated with ⌈
    static class ceil extends Operation {
        public String symbol() { return "⌈"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.ceil(a);
        }
    }
    public static APLTensor ceil(APLTensor a) {
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = Math.ceil(c[i]);
        return new APLTensor(c,a.shape());
    }
    
    // Monadic function associated with ⌊
    static class floor extends Operation {
        public String symbol() { return "⌊"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.floor(a);
        }
    }
    public static APLTensor floor(APLTensor a) {
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = Math.floor(c[i]);
        return new APLTensor(c,a.shape());
    }
    
    // Monadic function associated with ↑
    static class head extends Operation {
        public String symbol() { return "↑"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.head(a);
        }
    }
    public static APLTensor head(APLTensor a) {
        return new APLTensor(a.get(0));
    }
    
    // Monadic function associated with ⍴
    static class shape extends Operation {
        public String symbol() { return "⍴"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.shape(a);
        }
    }
    public static APLTensor shape(APLTensor a) {
        return a.shapeAsTensor();
    }
    
    // Monadic function associated with |
    static class abs extends Operation {
        public String symbol() { return "|"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.abs(a);
        }
    }
    public static APLTensor abs(APLTensor a) {
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = Math.abs(c[i]);
        return new APLTensor(c,a.shape());
    }
    
    // Monadic function associated with ⍳
    static class indices extends Operation {
        public String symbol() { return "⍳"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.indices(a);
        }
    }
    public static APLTensor indices(APLTensor a) {
        if (a.length() > 1) {
            log("LENGTH ERROR AT OPERATOR ⍳");
            return null;
        }
        if (a.get(0) < 0) {
            log("DOMAIN ERROR AT OPERATOR ⍳");
            return null;
        }
        double[] c = new double[(int)a.get(0)];
        for (int i = 0; i < c.length; i++)
            c[i] = i + 1;
        return new APLTensor(c);
    }
    
    // Monadic function associated with *
    static class exp extends Operation {
        public String symbol() { return "*"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.exp(a);
        }
    }
    public static APLTensor exp(APLTensor a) {
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = Math.exp(c[i]);
        return new APLTensor(c,a.shape());
    }
    
    // Monadic function associated with -
    static class neg extends Operation {
        public String symbol() { return "-"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.neg(a);
        }
    }
    public static APLTensor neg(APLTensor a) {
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = -c[i];
        return new APLTensor(c,a.shape());
    }
    
    // Monadic function associated with ×
    static class signum extends Operation {
        public String symbol() { return "×"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.signum(a);
        }
    }
    public static APLTensor signum(APLTensor a) {
        double[] c = a.values();
        for (int i = 0; i < c.length; i++) {
            if (c[i] > 0)
                c[i] = 1;
            else if (c[i] < 0)
                c[i] = -1;
            else
                c[i] = 0;
        }
        return new APLTensor(c,a.shape());
    }
    
    // Monadic function associated with ÷
    static class reciprocal extends Operation {
        public String symbol() { return "÷"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.reciprocal(a);
        }
    }
    public static APLTensor reciprocal(APLTensor a) {
        double[] c = a.values();
        for (int i = 0; i < c.length; i++) {
            if (a.get(i) == 0) {
                log("DOMAIN ERROR AT OPERATOR ÷");
                return null;
            }
            c[i] = 1.0 / c[i];
        }
        return new APLTensor(c,a.shape());
    }
    
    // Monadic function associated with ○
    static class pi extends Operation {
        public String symbol() { return "○"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.pi(a);
        }
    }
    public static APLTensor pi(APLTensor a) {
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = Math.PI * c[i];
        return new APLTensor(c,a.shape());
    }
    
    // Monadic function associated with ,
    static class ravel extends Operation {
        public String symbol() { return ","; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.ravel(a);
        }
    }
    public static APLTensor ravel(APLTensor a) {
        return new APLTensor(a.values(), a.length());
    }
    
    // Monadic function associated with ⍟
    static class ln extends Operation {
        public String symbol() { return "⍟"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.ln(a);
        }
    }
    public static APLTensor ln(APLTensor a) {
        double[] c = a.values();
        for (int i = 0; i < c.length; i++) {
            if (c[i] < 0) {
                log("DOMAIN ERROR AT OPERATOR ⍟");
                return null;
            }
            c[i] = Math.log(c[i]);
        }
        return new APLTensor(c,a.shape());
    }
    
    // Monadic function associated with ⌽
    static class reverse extends Operation {
        public String symbol() { return "⌽"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.reverse(a);
        }
    }
    public static APLTensor reverse(APLTensor a) {
        double[] c = a.values();
        if (a.dimensions() == 1) {
            for (int i = 0; i < c.length; i++)
                c[i] = a.get(c.length - i - 1);
            return new APLTensor(c,a.shape());
        }
        APLTensor[] axes = a.alongAxis(0);
        for (int i = 0; i < axes.length; i++) {
            axes[i] = reverse(axes[i]);
        }
        APLTensor ret = APLTensor.mergeAxes(0, axes);
        ret.reshape(a.shape());
        return ret;
    }
    
    // Monadic function associated with ⊖
    static class reverse2 extends Operation {
        public String symbol() { return "⌽"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.reverse2(a);
        }
    }
    public static APLTensor reverse2(APLTensor a) {
        double[] c = a.values();
        if (a.dimensions() == 1) {
            for (int i = 0; i < c.length; i++)
                c[i] = a.get(c.length - i - 1);
            return new APLTensor(c,a.shape());
        }
        APLTensor[] axes = a.alongAxis(a.dimensions()-1);
        for (int i = 0; i < axes.length; i++) {
            axes[i] = reverse2(axes[i]);
        }
        APLTensor ret = APLTensor.mergeAxes(a.dimensions()-1, axes);
        ret.reshape(a.shape());
        return ret;
    }
    
    // Monadic function associated with ⍉
    static class trans extends Operation {
        public String symbol() { return "⍉"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.trans(a);
        }
    }
    public static APLTensor trans(APLTensor a) {
        if (a.dimensions() == 1) {
            return a.clone();
        }
        
        int[] newshape = new int[a.dimensions()];
        for (int i = 0; i < a.dimensions(); i++)
            newshape[i] = a.shape()[a.dimensions() - i - 1];
        
        APLTensor ret = new APLTensor(newshape);
        
        for (int i = 0; i < a.length(); i++) {
            int[] index = a.index(i);
            int[] newindex = new int[index.length];
            for (int j = 0; j < a.dimensions(); j++)
                newindex[j] = index[a.dimensions() - j - 1];
            ret.set(a.get(i), newindex);
        }
        
        return ret;
    }
    
    // Monadic function associated with !
    static class factorial extends Operation {
        public String symbol() { return "!"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.factorial(a);
        }
    }
    public static APLTensor factorial(APLTensor a) {
        double[] c = a.values();
        for (int i = 0; i < c.length; i++) {
            if (c[i] < 0) {
                log("DOMAIN ERROR AT OPERATOR !");
                return null;
            }
            for (int j = (int)c[i]-1; j > 0; j--)
                c[i] *= j;
            c[i] = c[i] == 0 ? 1 : c[i];
        }
        return new APLTensor(c,a.shape());
    }
    
    // Monadic function associated with ⍕
    static class round extends Operation {
        public String symbol() { return "⍕"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.round(a);
        }
    }
    public static APLTensor round(APLTensor a) {
        double[] c = a.values();
        for (int i = 0; i < c.length; i++) {
            c[i] = Math.round(c[i]);
        }
        return new APLTensor(c,a.shape());
    }
    
    
    //========================================//
    //            Dyadic Operators            //
    //========================================//
    
    // Dyadic function associated with ∊
    static class contains extends Operation {
        public String symbol() { return "∊"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.contains(a, b);
        }
    }
    public static APLTensor contains(APLTensor a, APLTensor b) {
        double[] c = a.values();
        for (int i = 0; i < c.length; i++) {
            double temp = 0;
            for (int j = 0; j < b.length(); j++) {
                temp = c[i] == b.get(j) ? 1 : temp;
            }
            c[i] = temp;
        }
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ↑
    static class take extends Operation {
        public String symbol() { return "↑"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.take(a, b);
        }
    }
    public static APLTensor take(APLTensor a, APLTensor b) {
        if (a.length() != b.shape().length) {
            log("LENGTH ERROR AT OPERATOR ↑");
            return null;
        }
        
        APLTensor ret = b.clone();
        for (int i = 0; i < a.length(); i++) {
            int take = (int)a.get(i);
            APLTensor[] axes = ret.alongAxis(i);
            APLTensor[] newaxes = new APLTensor[Math.abs(take)];
            if (take < 0) {
                take = -1 * take;
                for (int j = 0; j < take; j++)
                    newaxes[j] = axes[j + axes.length - take];
            } else {
                for (int j = 0; j < take; j++)
                    newaxes[j] = axes[j];
            }
            ret = APLTensor.mergeAxes(i, newaxes);
        }
        
        return ret;
        
    }
    
    // Dyadic function associated with ↓
    static class drop extends Operation {
        public String symbol() { return "↓"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.drop(a, b);
        }
    }
    public static APLTensor drop(APLTensor a, APLTensor b) {
        if (a.length() != b.shape().length) {
            log("LENGTH ERROR AT OPERATOR ↑");
            return null;
        }
        
        APLTensor ret = b.clone();
        for (int i = 0; i < a.length(); i++) {
            int drop = (int)a.get(i);
            APLTensor[] axes = ret.alongAxis(i);
            APLTensor[] newaxes = new APLTensor[axes.length - Math.abs(drop)];
            if (drop < 0) {
                drop = -1 * drop;
                for (int j = 0; j < axes.length - drop; j++)
                    newaxes[j] = axes[j];
            } else {
                for (int j = 0; j < axes.length - drop; j++)
                    newaxes[j] = axes[j + drop];
            }
            ret = APLTensor.mergeAxes(i, newaxes);
        }
        
        return ret;
    }
    
    // Dyadic function associated with /
    static class compress extends Operation {
        public String symbol() { return "/"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.compress(a, b);
        }
    }
    public static APLTensor compress(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        int l = 0;
        for (int i = 0; i < a.length(); i++)
            if (a.get(i) >= 0)
                l++;
        if (l != b.length()) {
            log("LENGTH ERROR AT OPERATOR /");
            return null;
        }
        l = 0;
        for (int i = 0; i < a.length(); i++)
            l += Math.abs(a.get(i));
        double[] c = new double[l];
        int bi = 0, ci = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.get(i) < 0) {
                for (int j = 0; j < Math.abs(a.get(i)); j++) {
                    c[ci++] = 0;
                }
            } else {
                for (int j = 0; j < a.get(i); j++) {
                    c[ci++] = b.get(bi);
                }
                bi++;
            }
        }
        return new APLTensor(c, l);
    }
    
    // Dyadic function associated with ⊥
    static class base extends Operation {
        public String symbol() { return "⊥"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.base(a, b);
        }
    }
    public static APLTensor base(APLTensor a, APLTensor b) {
        if (a.dimensions() != 1) {
            log("LENGTH ERROR AT OPERATOR ⊥");
            return null;
        }
        if (a.length() == 1) {
            a.reshape(b.shape()[0]);
        } else if (b.shape()[0] != a.shape()[0]) {
            log("LENGTH ERROR AT OPERATOR ⊥");
            return null;
        }
        
        int[] newshape;
        if (b.dimensions() == 1)
            newshape = new int[] {1};
        else {
            newshape = new int[b.dimensions() - 1];
            for (int i = 1; i < b.dimensions(); i++)
                newshape[i - 1] = b.shape()[i];
        }
        
        
        APLTensor[] axes;
        if (b.dimensions() == 1)
            axes = new APLTensor[] {b};
        else {
            APLTensor b2 = b.clone();
            b2.reshape(a.length(), b.length()/a.length());
            axes = b2.alongAxis(1);
        }
        
        int m = 1;
        for (int i = 0; i < a.length(); i++)
            m *= (int)a.get(i);
        
        APLTensor ret = new APLTensor(newshape);
        int numbers = b.length() / a.length();
        for (int i = 0; i < numbers; i++) {
            axes[i] = trans(axes[i]);
            double term = m;
            double x = 0;
            for (int j = 0; j < a.length(); j++) {
                term /= a.get(j);
                x += axes[i].get(j) * term;
            }
            ret.set(x, i);
        }
        return ret;
    }
    
    // Dyadic function associated with ⊤
    static class antibase extends Operation {
        public String symbol() { return "⊤"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.antibase(a, b);
        }
    }
    public static APLTensor antibase(APLTensor a, APLTensor b) {
        if (a.dimensions() != 1) {
            log("LENGTH ERROR AT OPERATOR ⊤");
            return null;
        }
        int[] newshape;
        if (a.length() == 1)
            newshape = b.shape();
        else if (b.length() == 1)
            newshape = a.shape();
        else {
            newshape = new int[a.dimensions() + b.dimensions()];
            newshape[0] = a.shape()[0];
            for (int i = 0; i < b.dimensions(); i++)
                newshape[i+1] = b.shape()[i];
        }
        
        int m = 1;
        for (int i = 0; i < a.length(); i++)
            m *= (int)a.get(i);
        
        
        int numbers = b.length();
        APLTensor[] axes = new APLTensor[numbers];
        
        APLTensor ret = new APLTensor(newshape);
        int digits = a.length() * b.length();
        int index = 0;
        for (int i = 0; i < numbers; i++) {
            axes[i] = new APLTensor(a.shape());
            double remainder = b.get(i);
            double term = m;
            for (int j = 0; j < a.length() - 1; j++) {
                term /= a.get(j);
                int k = index++;
                axes[i].set((int)(remainder / term) % a.get(j), j);
                remainder -= axes[i].get(j) * term;
            }
            axes[i].set(remainder % a.get(a.length() - 1), a.length() - 1);
            axes[i] = trans(axes[i]);
        }
        ret = APLTensor.mergeAxes(1, axes);
        ret.reshape(newshape);
        return ret;
    }
    
    // Dyadic function associated with ?
    static class deal extends Operation {
        public String symbol() { return "?"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.deal(a, b);
        }
    }
    public static APLTensor deal(APLTensor a, APLTensor b) {
        if (a.length() != 1 || b.length() != 1) {
            log("LENGTH ERROR AT OPERATOR ?");
            return null;
        }
        if (a.get(0) > b.get(0)) {
            log("DOMAIN ERROR AT OPERATOR ?");
            return null;
        }
        
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 1; i <= (int)b.get(0); i++)
            nums.add(new Integer(i));
        Collections.shuffle(nums);
        
        APLTensor c = new APLTensor((int)a.get(0));
        for (int i = 0; i < c.length(); i++)
            c.set((double)nums.get(i), i);
        return c;
    }
    
    // Dyadic function associated with ⍴
    static class reshape extends Operation {
        public String symbol() { return "⍴"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.reshape(a, b);
        }
    }
    public static APLTensor reshape(APLTensor a, APLTensor b) {
        APLTensor c = b.clone();
        c.reshape(a.values());
        return c;
    }
    
    // Dyadic function associated with ,
    static class concat extends Operation {
        public String symbol() { return ","; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.concat(a, b);
        }
    }
    public static APLTensor concat(APLTensor a, APLTensor b) {
        if (a.dimensions() == 1 && b.dimensions() == 1) {
            double[] c = new double[a.length() + b.length()];
            for (int i = 0; i < a.length(); i++)
                c[i] = a.get(i);
            for (int i = 0; i < b.length(); i++)
                c[a.length() + i] = b.get(i);
            return new APLTensor(c);
        }
        
        if (a.shape()[0] != b.shape()[0]) {
            log("LENGTH ERROR AT OPERATOR ,");
            return null;
        }
        
        APLTensor[] a_axes = a.alongAxis(0);
        APLTensor[] b_axes = b.alongAxis(0);
        APLTensor[] c_axes = new APLTensor[a_axes.length];
        for (int i = 0; i < a_axes.length; i++) {
            c_axes[i] = concat(a_axes[i], b_axes[i]);
        }
        
        return APLTensor.mergeAxes(0, c_axes);
    }
    
    // Dyadic function associated with ⍳
    static class indexof extends Operation {
        public String symbol() { return "⍳"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.indexof(a, b);
        }
    }
    public static APLTensor indexof(APLTensor a, APLTensor b) {
        int l = b.length();
        double[] c = new double[l];
        for (int i = 0; i < l; i++) {
            c[i] = a.length() + 1;
            for (int j = 0; j < a.length(); j++) {
                if (b.get(i) == a.get(j)) {
                    c[i] = j + 1;
                    continue;
                }
            }
        }
        return new APLTensor(c, b.shape());
    }
    
    // Dyadic function associated with |
    static class mod extends Operation {
        public String symbol() { return "|"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.mod(a, b);
        }
    }
    public static APLTensor mod(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR |");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = b.get(i) % a.get(i);
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ○
    static class trig extends Operation {
        public String symbol() { return "○"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.trig(a, b);
        }
    }
    public static APLTensor trig(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR ○");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++) {
            switch ((int)a.get(i)) {
                    
                    
                    // PYTHAGOREAN FUNCTIONS
                case 0:
                    c[i] = Math.sqrt(1 - Math.pow(b.get(i),2));
                    break;
                case 4:
                    c[i] = Math.sqrt(1 + Math.pow(b.get(i),2));
                    break;
                case -4:
                    c[i] = Math.sqrt(-1 + Math.pow(b.get(i),2));
                    break;
                case 8:
                    c[i] = Math.sqrt(-1 - Math.pow(b.get(i),2));
                    break;
                case -8:
                    c[i] = -Math.sqrt(-1 - Math.pow(b.get(i),2));
                    break;
                    
                    
                    // TRIGONOMETRIC
                case 1:
                    c[i] = Math.sin(b.get(i));
                    break;
                case -1:
                    c[i] = Math.asin(b.get(i));
                    break;
                case 2:
                    c[i] = Math.cos(b.get(i));
                    break;
                case -2:
                    c[i] = Math.acos(b.get(i));
                    break;
                case 3:
                    c[i] = Math.tan(b.get(i));
                    break;
                case -3:
                    c[i] = Math.atan(b.get(i));
                    break;
                    
                    
                    // HYPERBOLIC
                case 5:
                    c[i] = Math.sinh(b.get(i));
                    break;
                case 6:
                    c[i] = Math.cosh(b.get(i));
                    break;
                case 7:
                    c[i] = Math.tanh(b.get(i));
                    break;
                case -5:
                    c[i] = Math.log(b.get(i) + Math.sqrt(b.get(i)*b.get(i) + 1.0));
                    break;
                case -6:
                    c[i] = Math.log(b.get(i) + Math.sqrt(b.get(i)*b.get(i) - 1.0));;
                    break;
                case -7:
                    c[i] = 0.5*Math.log( (b.get(i) + 1.0) / (b.get(i) - 1.0) );
                    break;
                    
                    
                default:
                    log("UNKNOWN TRIG OPTION: " + (int)a.get(i));
                    return null;
            }
        }
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ⍟
    static class log extends Operation {
        public String symbol() { return "⍟"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.log(a, b);
        }
    }
    public static APLTensor log(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR ⍟");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = Math.log(b.get(i)) / Math.log(a.get(i));
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with !
    static class combinations extends Operation {
        public String symbol() { return "!"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.combinations(a, b);
        }
    }
    public static APLTensor combinations(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR !");
            return null;
        }
        
        double[] c = a.values();
        for (int i = 0; i < c.length; i++) {
            int x = (int)b.get(i);
            int y = (int)a.get(i);
            int z = (int)(x - y);
            if (x == y) {
                c[i] = 1;
                continue;
            } else if (x == 0 || (y*z) < 0) {
                c[i] = 0;
                continue;
            } else if (x <= 0 || y <= 0 || z <= 0) {
                log("DOMAIN ERROR AT OPERATOR !");
                return null;
            }
            
            int fx = 1;
            int fy = 1;
            int fz = 1;
            for (int j = x; j > 1; j--)
                fx *= j;
            for (int j = y; j > 1; j--)
                fy *= j;
            for (int j = z; j > 1; j--)
                fz *= j;
            c[i] = fx / (fy * fz);
        }
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with +
    static class add extends Operation {
        public String symbol() { return "+"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.add(a, b);
        }
    }
    public static APLTensor add(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR +");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = a.get(i) + b.get(i);
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with -
    static class sub extends Operation {
        public String symbol() { return "-"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.sub(a, b);
        }
    }
    public static APLTensor sub(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR -");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = a.get(i) - b.get(i);
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ×
    static class mul extends Operation {
        public String symbol() { return "×"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.mul(a, b);
        }
    }
    public static APLTensor mul(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR ×");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = a.get(i) * b.get(i);
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ÷
    static class div extends Operation {
        public String symbol() { return "÷"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.div(a, b);
        }
    }
    public static APLTensor div(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR +");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = a.get(i) / b.get(i);
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with *
    static class pow extends Operation {
        public String symbol() { return "*"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.pow(a, b);
        }
    }
    public static APLTensor pow(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR *");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = Math.pow(a.get(i), b.get(i));
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with <
    static class less extends Operation {
        public String symbol() { return "<"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.less(a, b);
        }
    }
    public static APLTensor less(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR <");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = a.get(i) < b.get(i) ? 1 : 0;
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ≤
    static class leq extends Operation {
        public String symbol() { return "≤"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.leq(a, b);
        }
    }
    public static APLTensor leq(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR ≤");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = a.get(i) <= b.get(i) ? 1 : 0;
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with =
    static class equ extends Operation {
        public String symbol() { return "="; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.equ(a, b);
        }
    }
    public static APLTensor equ(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR =");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = a.get(i) == b.get(i) ? 1 : 0;
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ≡
    static class equivilent extends Operation {
        public String symbol() { return "≡"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.equivilent(a, b);
        }
    }
    public static APLTensor equivilent(APLTensor a, APLTensor b) {
        if (a.equals(b))
            return new APLTensor(1.0);
        return new APLTensor(0.0);
    }
    
    // Dyadic function associated with ≥
    static class greq extends Operation {
        public String symbol() { return "≥"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.greq(a, b);
        }
    }
    public static APLTensor greq(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR ≥");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = a.get(i) >= b.get(i) ? 1 : 0;
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with >
    static class greater extends Operation {
        public String symbol() { return ">"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.greater(a, b);
        }
    }
    public static APLTensor greater(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR >");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = a.get(i) > b.get(i) ? 1 : 0;
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ≠
    static class neq extends Operation {
        public String symbol() { return "≠"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.neq(a, b);
        }
    }
    public static APLTensor neq(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR ≠");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = a.get(i) != b.get(i) ? 1 : 0;
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ∨
    static class gcd extends Operation {
        public String symbol() { return "∨"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.gcd(a, b);
        }
    }
    public static APLTensor gcd(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR ∨");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++) {
            c[i] = gcd(a.get(i), b.get(i));
        }
        return new APLTensor(c, a.shape());
    }
    public static double gcd(double x, double y) {
        while (y > 0) {
            double temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }
    
    // Dyadic function associated with ∧
    static class lcm extends Operation {
        public String symbol() { return "∧"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.lcm(a, b);
        }
    }
    public static APLTensor lcm(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR ∧");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++) {
            double d = gcd(a.get(i), b.get(i));
            if (d == 0) c[i] = 0;
            else c[i] = a.get(i) * (b.get(i) / d);
        }
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ⍱
    static class nor extends Operation {
        public String symbol() { return "⍱"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.nor(a, b);
        }
    }
    public static APLTensor nor(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR ⍱");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = !(a.get(i) != 0 || b.get(i) != 0) ? 1 : 0;
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ⍲
    static class nand extends Operation {
        public String symbol() { return "⍲"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.nand(a, b);
        }
    }
    public static APLTensor nand(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR ⍲");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = !(a.get(i) != 0 && b.get(i) != 0) ? 1 : 0;
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ⌈
    static class max extends Operation {
        public String symbol() { return "⌈"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.max(a, b);
        }
    }
    public static APLTensor max(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR ⌈");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = Math.max(a.get(i), b.get(i));
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ⌊
    static class min extends Operation {
        public String symbol() { return "⌊"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.min(a, b);
        }
    }
    public static APLTensor min(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR ⌊");
            return null;
        }
        double[] c = a.values();
        for (int i = 0; i < c.length; i++)
            c[i] = Math.min(a.get(i), b.get(i));
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ⍕
    static class format extends Operation {
        public String symbol() { return "⍕"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.format(a, b);
        }
    }
    public static APLTensor format(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        if (b.length() == 1) {
            b.reshape(a.shape());
        }
        if (!Arrays.equals(a.shape(), b.shape())) {
            log("LENGTH ERROR AT OPERATOR ⍕");
            return null;
        }
        double[] c = b.values();
        for (int i = 0; i < c.length; i++) {
            int dec = (int)a.get(i);
            c[i] *= Math.pow(10, dec);
            c[i] = Math.round(c[i]);
            c[i] /= Math.pow(10, dec);
        }
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ⍉
    static class permute extends Operation {
        public String symbol() { return "⍉"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.permute(a, b);
        }
    }
    public static APLTensor permute(APLTensor a, APLTensor b) {
        if (a.length() <= 1 || a.length() != b.dimensions()) {
            log("LENGTH ERROR AT OPERATOR ⍉");
            return null;
        }
        
        int[] newshape = new int[b.dimensions()];
        for (int i = 0; i < b.dimensions(); i++) {
            if (a.get(i) > b.dimensions() || a.get(i) <= 0) {
                log("DOMAIN ERROR AT OPERATOR ⍉");
                return null;
            }
            newshape[i] = b.shape()[(int)a.get(i)-1];
        }
        
        APLTensor ret = new APLTensor(newshape);
        
        for (int i = 0; i < b.length(); i++) {
            int[] index = b.index(i);
            int[] newindex = new int[index.length];
            for (int j = 0; j < b.dimensions(); j++)
                newindex[j] = index[(int)a.get(j)-1];
            ret.set(b.get(i), newindex);
        }
        
        return ret;
    }
}
