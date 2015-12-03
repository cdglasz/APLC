import java.util.*;
import java.text.DecimalFormat;
public class APLOps {
    
    private static String errorLog = "";
    
    private static void log(String error) {
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
        public String symbol() { return "/" + op.symbol(); }
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
            if (a.dimensions() > 2) {
                log("OPERATOR " + symbol() + " NOT SUPPORTED FOR TENSORS OF >2 DIMENSIONS");
                return null;
            }
            int l = a.length();
            
            int[] newshape = new int[a.shape().length-1];
            for (int i = 0; i < a.dimensions() - 1; i++)
                newshape[i] = a.shape()[i];
            
            APLTensor[] aa = a.alongAxis(0);
            
            APLTensor running = new APLTensor(newshape);
            for (int i = 0; i < aa.length; i++) {
                running.set(aa[i].get(0),i);
                for (int j = 1; j < aa[i].length(); j++)
                    running.set(op.exec(new APLTensor(running.get(i)),
                                        new APLTensor(aa[i].get(j))).get(0), i);
            }
            return running;
        }
    }
    
    static class scan extends Operation {
        public String symbol() { return "\\" + op.symbol(); }
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
            if (a.dimensions() > 2) {
                log("OPERATOR " + symbol() + " NOT SUPPORTED FOR TENSORS OF >2 DIMENSIONS");
                return null;
            }
            APLTensor[] aa = a.alongAxis(0);
            APLTensor running = new APLTensor(a.shape());
            int idx = 0;
            for (int i = 0; i < aa.length; i++) {
                running.set(aa[i].get(0),idx++);
                for (int j = 1; j < aa[i].length(); j++)
                    running.set(op.exec(new APLTensor(running.get(idx-1)),
                                        new APLTensor(aa[i].get(j))).get(0), idx++);
            }
            return running;
        }
    }
    
    static class each extends Operation {
        public String symbol() { return "¨" + op.symbol(); }
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
        double[] c = a.values();
        Arrays.sort(c);
        return new APLTensor(c,a.shape());
    }
    
    // Monadic function associated with ⍒
    static class sortdown extends Operation {
        public String symbol() { return "⍒"; }
        public APLTensor exec(APLTensor dc, APLTensor a) {
            return APLOps.sortdown(a);
        }
    }
    public static APLTensor sortdown(APLTensor a) {
        double[] c = a.values();
        Arrays.sort(c);
        return reverse(new APLTensor(c,a.shape()));
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
        for (int i = 0; i < c.length; i++)
            c[i] = a.get(c.length - i - 1);
        return new APLTensor(c,a.shape());
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
        
        int[] shape = b.shape();
        int[] newshape = new int[shape.length];
        for (int i = 0; i < shape.length; i++)
            newshape[i] = (int)a.get(i);
        
        APLTensor c = b.clone();
        c.restrict(newshape);
        
        //        for (int i = 0; i < a.length(); i++) {
        //            int l = (int)a.get(0);
        //            boolean first = l > 0;
        //            l = Math.abs(l);
        //            double[] c = new double[l];
        //            if (first) {
        //                for (int i = 0; i < l; i++) {
        //                    c[i] = i < b.length() ? b.get(i) : 0;
        //                }
        //            } else {
        //                for (int i = 0; i < l; i++) {
        //                    int j = b.length() - l + i;
        //                    c[i] = j >= 0 ? b.get(j) : 0;
        //                }
        //            }
        //        }
        
        return c;
    }
    
    // Dyadic function associated with ↓
    static class drop extends Operation {
        public String symbol() { return "↓"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.drop(a, b);
        }
    }
    public static APLTensor drop(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape().length);
        }
        
        int[] shape = b.shape();
        int[] newshape = new int[shape.length];
        for (int i = 0; i < shape.length; i++)
            newshape[i] = shape[i] - (int)Math.abs(a.get(i));
        
        
        
        int l = a.length();
        if (a.length() != b.shape().length) {
            log("LENGTH ERROR AT OPERATOR ↓");
            return null;
        }
        l = (int)a.get(0);
        if (Math.abs(l) >= b.length()) {
            return null;
        }
        boolean first = l < 0;
        l = b.length() - Math.abs(l);
        double[] c = new double[l];
        if (first) {
            for (int i = 0; i < l; i++) {
                c[i] = i < b.length() ? b.get(i) : 0;
            }
        } else {
            for (int i = 0; i < l; i++) {
                int j = b.length() - l + i;
                c[i] = j >= 0 ? b.get(j) : 0;
            }
        }
        
        return new APLTensor(c, newshape);
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
    static class decode extends Operation {
        public String symbol() { return "⊥"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.decode(a, b);
        }
    }
    public static APLTensor decode(APLTensor a, APLTensor b) {
        if (a.length() == 1) {
            a.reshape(b.shape());
        }
        
        if (a.length() != b.length()) {
            log("LENGTH ERROR AT OPERATOR ⊥");
            return null;
        }
        
        int[] newshape;
        if (a.shape().length == 1)
            newshape = new int[] {1};
        else {
            newshape = new int[a.shape().length-1];
            for (int i = 1; i < a.shape().length; i++)
                newshape[i-1] = a.shape()[i];
        }
        
        double[] c = (new APLTensor(newshape)).values();
        for (int i = 0; i < b.length(); i++)
            c[0] += b.get(i) * Math.pow(a.get(0),b.length()-i-1);
        return new APLTensor(c, newshape);
    }
    
    // Dyadic function associated with ⊤
    static class encode extends Operation {
        public String symbol() { return "⊤"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.encode(a, b);
        }
    }
    public static APLTensor encode(APLTensor a, APLTensor b) {
        if (a.shape().length != 1 || b.shape().length != 1) {
            log("LENGTH ERROR AT OPERATOR ⊤");
            return null;
        }
        int[] newshape = new int[] {b.length(), a.length()};
        double[] c = new APLTensor(newshape).values();
        for (int i = 0; i < b.length(); i++) {
            double remainder = b.get(i);
            for (int j = 0; j < a.length(); j++) {
                double power = Math.pow(a.get(j),a.length()-j-1);
                c[i*a.length() + j] = (int)(remainder / power);
                remainder -= c[i*a.length() + j] * power;
            }
        }
        return new APLTensor(c, newshape);
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
        
        int[] ashape = a.shape();
        int[] bshape = b.shape();
        int[] sharedshape = ashape;
        sharedshape[ashape.length-1] = bshape[bshape.length-1];
        if (!Arrays.equals(sharedshape, bshape)) {
            log("LENGTH ERROR AT OPERATOR ,");
            return null;
        }
        
        int[] newshape = ashape;
        newshape[ashape.length-1] += bshape[bshape.length-1];
        
        int newlength = a.length() + b.length();
        double[] c = new double[newlength];
        for (int i = 0; i < a.length(); i++)
            c[i] = a.get(i);
        for (int i = 0; i < b.length(); i++)
            c[a.length() + i] = b.get(i);
        
        return new APLTensor(c,newshape);
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
            c[i] = a.get(i) % b.get(i);
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
                case 1:
                    c[i] = Math.sin(b.get(i));
                    break;
                case 2:
                    c[i] = Math.cos(b.get(i));
                    break;
                case 3:
                    c[i] = Math.tan(b.get(i));
                    break;
                case 5:
                    c[i] = Math.sinh(b.get(i));
                    break;
                case 6:
                    c[i] = Math.cosh(b.get(i));
                    break;
                case 7:
                    c[i] = Math.tanh(b.get(i));
                    break;
                case -1:
                    c[i] = Math.asin(b.get(i));
                    break;
                case -2:
                    c[i] = Math.acos(b.get(i));
                    break;
                case -3:
                    c[i] = Math.atan(b.get(i));
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
    static class or extends Operation {
        public String symbol() { return "∨"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.or(a, b);
        }
    }
    public static APLTensor or(APLTensor a, APLTensor b) {
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
        for (int i = 0; i < c.length; i++)
            c[i] = a.get(i) != 0 || b.get(i) != 0 ? 1 : 0;
        return new APLTensor(c, a.shape());
    }
    
    // Dyadic function associated with ∧
    static class and extends Operation {
        public String symbol() { return "∧"; }
        public APLTensor exec(APLTensor a, APLTensor b) {
            return APLOps.and(a, b);
        }
    }
    public static APLTensor and(APLTensor a, APLTensor b) {
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
        for (int i = 0; i < c.length; i++)
            c[i] = a.get(i) != 0 && b.get(i) != 0 ? 1 : 0;
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
}
