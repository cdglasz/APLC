import java.util.*;
import java.text.DecimalFormat;
public class APLOperators {
    public static String toString(double[] a) {
        DecimalFormat df = new DecimalFormat("#.##########");
        String str = "";
        for (int i = 0; i < a.length; i++)
            str += df.format(a[i]) + " ";
        return str;
    }
    
    //========================================//
    //           Adverb Operators             //
    //========================================//
    public static double[] across(Operation op, double[] a) {
        int l = a.length;
        double[] running = new double[] {a[l-1]};
        for (int i = l-2; i >= 0; i--)
            running = op.exec(new double[] {a[i]}, running);
        return running;
    }
    
    //========================================//
    //           Monadic Operators            //
    //========================================//
    public static double[] not(double[] a) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++)
            c[i] = a[i] == 0 ? 1 : 0;
        return c;
    }
    
    public static double[] sort(double[] a) {
        double[] c = a.clone();
        Arrays.sort(c);
        return c;
    }
    
    public static double[] roll(double[] a) {
        int l = a.length;
        Random rand = new Random();
        int  n = rand.nextInt(l);
        return new double[] {a[n]};
    }
    
    public static double[] ceil(double[] a) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++)
            c[i] = Math.ceil(a[i]);
        return c;
    }
    
    public static double[] floor(double[] a) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++)
            c[i] = Math.floor(a[i]);
        return c;
    }
    
    public static double[] shape(double[] a) {
        return new double[] {a.length};
    }
    
    public static double[] abs(double[] a) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++)
            c[i] = Math.abs(a[i]);
        return c;
    }
    
    public static double[] indices(double[] a) {
        int l = (int)a[0];
        double[] c = new double[l];
        for (int i = 0; i < l; i++)
            c[i] = i + 1;
        return c;
    }
    
    public static double[] exp(double[] a) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++)
            c[i] = Math.exp(a[i]);
        return c;
    }
    
    public static double[] neg(double[] a) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++)
            c[i] = -a[i];
        return c;
    }
    
    public static double[] signum(double[] a) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++) {
            if (a[i] > 0)
                c[i] = 1;
            else if (a[i] < 0)
                c[i] = -1;
            else
                c[i] = 0;
        }
        return c;
    }
    
    public static double[] reciprocal(double[] a) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++)
            c[i] = 1.0 / a[i];
        return c;
    }
    
    public static double[] pi(double[] a) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++)
            c[i] = Math.PI * a[i];
        return c;
    }
    
    public static double[] ln(double[] a) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++)
            c[i] = Math.log(a[i]);
        return c;
    }
    
    public static double[] reverse(double[] a) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++)
            c[i] = a[l - i - 1];
        return c;
    }
    
    public static double[] factorial(double[] a) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++) {
            c[i] = 1;
            for (int j = 1; j <= a[i]; j++) {
                c[i] *= j;
            }
        }
        return c;
    }
    
    //========================================//
    //            Dyadic Operators            //
    //========================================//
    public static double[] contains(double[] a, double[] b) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++) {
            double temp = 0;
            for (int j = 0; j < b.length; j++) {
                temp = a[i] == b[j] ? 1 : temp;
            }
            c[i] = temp;
        }
        return c;
    }
    
    public static double[] take(double[] a, double[] b) {
        int l = a.length;
        if (l > 1) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        l = (int)a[0];
        boolean first = l > 0;
        l = Math.abs(l);
        double[] c = new double[l];
        if (first) {
            for (int i = 0; i < l; i++) {
                c[i] = i < b.length ? b[i] : 0;
            }
        } else {
            for (int i = 0; i < l; i++) {
                int j = b.length - l + i;
                c[i] = j >= 0 ? b[j] : 0;
            }
        }
        
        return c;
    }
    
    public static double[] drop(double[] a, double[] b) {
        int l = a.length;
        if (l > 1) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        l = (int)a[0];
        if (Math.abs(l) >= b.length) {
            return new double[0];
        }
        boolean first = l < 0;
        l = b.length - Math.abs(l);
        double[] c = new double[l];
        if (first) {
            for (int i = 0; i < l; i++) {
                c[i] = i < b.length ? b[i] : 0;
            }
        } else {
            for (int i = 0; i < l; i++) {
                int j = b.length - l + i;
                c[i] = j >= 0 ? b[j] : 0;
            }
        }
        
        return c;
    }
    
    public static double[] compress(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        int l = 0;
        for (int i = 0; i < a.length; i++)
            if (a[i] >= 0)
                l++;
        if (l != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        l = 0;
        for (int i = 0; i < a.length; i++)
            l += Math.abs(a[i]);
        double[] c = new double[l];
        int bi = 0, ci = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0) {
                for (int j = 0; j < Math.abs(a[i]); j++) {
                    c[ci++] = 0;
                }
            } else {
                for (int j = 0; j < a[i]; j++) {
                    c[ci++] = b[bi];
                }
                bi++;
            }
        }
        return c;
    }
    
    public static double[] decode(double[] a, double[] b) {
        if (a.length != 1) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[] {0};
        for (int i = 0; i < b.length; i++)
            c[0] += b[i] * Math.pow(a[0],b.length-i-1);
        return c;
    }
    
    public static double[] encode(double[] a, double[] b) {
        if (b.length != 1) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        double remainder = b[0];
        for (int i = 0; i < a.length; i++) {
            double power = Math.pow(a[i],a.length-i-1);
            c[i] = (int)(remainder / power);
            remainder -= c[i] * power;
        }
        return c;
    }
    
    public static double[] deal(double[] a, double[] b) {
        if (a.length != 1 || b.length != 1) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[(int)a[0]];
        double[] inds = indices(b);
        double[] options = indices(b);
        for (int i = 0; i < a[0]; i++) {
            int ind = (int)roll(inds)[0];
            c[i] = options[ind-1];
            inds = drop(new double[] {-1}, inds);
            options = concat(take(new double[] {ind-1},options),
                             drop(new double[] {ind},options));
        }
        return c;
    }
    
    public static double[] reshape(double[] a, double[] b) {
        if (a.length != 1) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        int l = (int)a[0];
        double[] c = new double[l];
        for (int i = 0; i < l; i++) {
            c[i] = b[i%b.length];
        }
        return c;
    }
    
    public static double[] concat(double[] a, double[] b) {
        double[] c = new double[a.length + b.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i];
        for (int i = 0; i < b.length; i++)
            c[a.length + i] = b[i];
        return c;
    }
    
    public static double[] mod(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] % b[i];
        return c;
    }
    
    public static double[] indexof(double[] a, double[] b) {
        int l = b.length;
        double[] c = new double[l];
        for (int i = 0; i < l; i++) {
            c[i] = a.length + 1;
            for (int j = 0; j < a.length; j++) {
                if (b[i] == a[j]) {
                    c[i] = j + 1;
                    continue;
                }
            }
        }
        return c;
    }
    
    public static double[] trig(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            switch ((int)a[i]) {
                case 1:
                    c[i] = Math.sin(b[i]);
                    break;
                case 2:
                    c[i] = Math.cos(b[i]);
                    break;
                case 3:
                    c[i] = Math.tan(b[i]);
                    break;
                case 5:
                    c[i] = Math.sinh(b[i]);
                    break;
                case 6:
                    c[i] = Math.cosh(b[i]);
                    break;
                case 7:
                    c[i] = Math.tanh(b[i]);
                    break;
                case -1:
                    c[i] = Math.asin(b[i]);
                    break;
                case -2:
                    c[i] = Math.acos(b[i]);
                    break;
                case -3:
                    c[i] = Math.atan(b[i]);
                    break;
                case -5:
                    c[i] = Math.log(b[i] + Math.sqrt(b[i]*b[i] + 1.0));
                    break;
                case -6:
                    c[i] = Math.log(b[i] + Math.sqrt(b[i]*b[i] - 1.0));;
                    break;
                case -7:
                    c[i] = 0.5*Math.log( (b[i] + 1.0) / (b[i] - 1.0) );
                    break;
                default:
                    System.err.println("UNKNOWN TRIG OPTION");
                    System.exit(1);
            }
        }
        return c;
    }
    
    public static double[] log(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = Math.log(b[i]) / Math.log(a[i]);
        return c;
    }
    
    public static double[] add(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] + b[i];
        return c;
    }
    
    public static double[] sub(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] - b[i];
        return c;
    }
    
    public static double[] mul(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] * b[i];
        return c;
    }
    
    public static double[] div(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] / b[i];
        return c;
    }
    public static double[] pow(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = Math.pow(a[i], b[i]);
        return c;
    }
    
    public static double[] less(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] < b[i] ? 1 : 0;
        return c;
    }
    
    public static double[] leq(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] <= b[i] ? 1 : 0;
        return c;
    }
    
    public static double[] equ(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] == b[i] ? 1 : 0;
        return c;
    }
    
    public static double[] greq(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] >= b[i] ? 1 : 0;
        return c;
    }
    
    public static double[] greater(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] > b[i] ? 1 : 0;
        return c;
    }
    
    public static double[] neq(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] != b[i] ? 1 : 0;
        return c;
    }
    
    public static double[] or(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] != 0 || b[i] != 0 ? 1 : 0;
        return c;
    }
    
    public static double[] and(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] != 0 && b[i] != 0 ? 1 : 0;
        return c;
    }
    
    public static double[] nor(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = !(a[i] != 0 || b[i] != 0) ? 1 : 0;
        return c;
    }
    
    public static double[] nand(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = !(a[i] != 0 && b[i] != 0) ? 1 : 0;
        return c;
    }
    
    public static double[] max(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = Math.max(a[i], b[i]);
        return c;
    }
    
    public static double[] min(double[] a, double[] b) {
        if (a.length == 1) {
            double val = a[0];
            a = new double[b.length];
            for (int i = 0; i < b.length; i++)
                a[i] = val;
        }
        if (b.length == 1) {
            double val = b[0];
            b = new double[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = val;
        }
        if (a.length != b.length) {
            System.err.println("LENGTH ERROR");
            System.exit(1);
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = Math.min(a[i], b[i]);
        return c;
    }
    
    
    
    
    
    
    
    static abstract class Operation {
        public abstract double[] exec(double[] a, double[] b);
    }
    
    static class Add extends Operation {
        public double[] exec(double[] a, double[] b) {
            return APLOperators.add(a, b);
        }
    }
}
