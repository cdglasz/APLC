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
    
    static abstract class Operation {
        public abstract double[] exec(double[] left, double[] right);
    }
    public static double[] exec(Operation op) {
        return op.exec(null, null);
    }
    
    public static double[] exec(Operation op, double[] right) {
        return op.exec(null, right);
    }
    
    public static double[] exec(Operation op, double[] left, double[] right) {
        return op.exec(left, right);
    }
    
    //========================================//
    //           Adverb Operators             //
    //========================================//
    static class across extends Operation {
        private Operation op;
        
        public across(Operation op) {
            this.op = op;
        }
        
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            double[] running = new double[] {a[l-1]};
            for (int i = l-2; i >= 0; i--)
                running = op.exec(new double[] {a[i]}, running);
            return running;
        }
    }
    
    static class each extends Operation {
        private Operation op;
        
        public each(Operation op) {
            this.op = op;
        }
        
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            double[] c = new double[a.length];
            for (int i = 0; i < a.length; i++)
                c[i] = op.exec(null, new double[] {a[i]})[0];
            return c;
        }
    }
    
    //========================================//
    //           Operationic Operators            //
    //========================================//
    static class clone extends Operation {
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            double[] c = new double[l];
            for (int i = 0; i < l; i++)
                c[i] = a[i];
            return c;
        }
    }
    
    static class not extends Operation {
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            double[] c = new double[l];
            for (int i = 0; i < l; i++)
                c[i] = a[i] == 0 ? 1 : 0;
            return c;
        }
    }
    
    static class sort extends Operation {
        public double[] exec(double[] dc, double[] a) {
            double[] c = a.clone();
            Arrays.sort(c);
            return c;
        }
    }
    
    static class sortdown extends Operation {
        public double[] exec(double[] dc, double[] a) {
            double[] c = a.clone();
            Arrays.sort(c);
            c = APLOperators.exec(new reverse(), c);
            return c;
        }
    }
    
    static class roll extends Operation {
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            Random rand = new Random();
            int  n = rand.nextInt(l);
            return new double[] {a[n]};
        }
    }
    
    static class ceil extends Operation {
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            double[] c = new double[l];
            for (int i = 0; i < l; i++)
                c[i] = Math.ceil(a[i]);
            return c;
        }
    }
    
    static class floor extends Operation {
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            double[] c = new double[l];
            for (int i = 0; i < l; i++)
                c[i] = Math.floor(a[i]);
            return c;
        }
    }
    
    static class shape extends Operation {
        public double[] exec(double[] dc, double[] a) {
            return new double[] {a.length};
        }
    }
    
    static class abs extends Operation {
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            double[] c = new double[l];
            for (int i = 0; i < l; i++)
                c[i] = Math.abs(a[i]);
            return c;
        }
    }
    
    static class indices extends Operation {
        public double[] exec(double[] dc, double[] a) {
            int l = (int)a[0];
            double[] c = new double[l];
            for (int i = 0; i < l; i++)
                c[i] = i + 1;
            return c;
        }
    }
    
    static class exp extends Operation {
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            double[] c = new double[l];
            for (int i = 0; i < l; i++)
                c[i] = Math.exp(a[i]);
            return c;
        }
    }
    
    static class neg extends Operation {
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            double[] c = new double[l];
            for (int i = 0; i < l; i++)
                c[i] = -a[i];
            return c;
        }
    }
    
    static class signum extends Operation {
        public double[] exec(double[] dc, double[] a) {
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
    }
    
    static class reciprocal extends Operation {
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            double[] c = new double[l];
            for (int i = 0; i < l; i++)
                c[i] = 1.0 / a[i];
            return c;
        }
    }
    
    static class pi extends Operation {
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            double[] c = new double[l];
            for (int i = 0; i < l; i++)
                c[i] = Math.PI * a[i];
            return c;
        }
    }
    
    static class ln extends Operation {
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            double[] c = new double[l];
            for (int i = 0; i < l; i++)
                c[i] = Math.log(a[i]);
            return c;
        }
    }
    
    static class reverse extends Operation {
        public double[] exec(double[] dc, double[] a) {
            int l = a.length;
            double[] c = new double[l];
            for (int i = 0; i < l; i++)
                c[i] = a[l - i - 1];
            return c;
        }
    }
    
    static class factorial extends Operation {
        public double[] exec(double[] dc, double[] a) {
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
    }
    
    //========================================//
    //            Operationic Operators            //
    //========================================//
    static class contains extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class take extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class drop extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class compress extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class decode extends Operation {
        public double[] exec(double[] a, double[] b) {
            if (a.length != 1) {
                System.err.println("LENGTH ERROR");
                System.exit(1);
            }
            double[] c = new double[] {0};
            for (int i = 0; i < b.length; i++)
                c[0] += b[i] * Math.pow(a[0],b.length-i-1);
            return c;
        }
    }
    
    static class encode extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class deal extends Operation {
        public double[] exec(double[] a, double[] b) {
            if (a.length != 1 || b.length != 1) {
                System.err.println("LENGTH ERROR");
                System.exit(1);
            }
            Operation indices = new indices();
            Operation concat = new concat();
            Operation roll = new roll();
            Operation drop = new drop();
            Operation take = new take();
            double[] c = new double[(int)a[0]];
            double[] inds = APLOperators.exec(indices, b);
            double[] options = APLOperators.exec(indices, b);
            for (int i = 0; i < a[0]; i++) {
                int ind = (int)APLOperators.exec(roll, inds)[0];
                c[i] = options[ind-1];
                inds = APLOperators.exec(drop, new double[] {-1}, inds);
                options = APLOperators.exec(concat,
                                            APLOperators.exec(take, new double[] {ind-1},options),
                                            APLOperators.exec(drop, new double[] {ind},options));
            }
            return c;
        }
    }
    
    static class reshape extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class concat extends Operation {
        public double[] exec(double[] a, double[] b) {
            double[] c = new double[a.length + b.length];
            for (int i = 0; i < a.length; i++)
                c[i] = a[i];
            for (int i = 0; i < b.length; i++)
                c[a.length + i] = b[i];
            return c;
        }
    }
    
    static class mod extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class indexof extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class trig extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class log extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class add extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class sub extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class mul extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class div extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class pow extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class less extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class leq extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class equ extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class greq extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class greater extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class neq extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class or extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class and extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class nor extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class nand extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class max extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
    
    static class min extends Operation {
        public double[] exec(double[] a, double[] b) {
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
    }
}
