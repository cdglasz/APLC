import java.util.*;
public class APLOperators {
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
		double[] c = new double[a.length];
		for (int i = 0; i < a.length; i++)
			c[i] = a[i] / b[i];
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
		double[] c = new double[a.length];
		for (int i = 0; i < a.length; i++)
			c[i] = Math.min(a[i], b[i]);
		return c;
	}

	public static double[] not(double[] a) {
		double[] c = new double[a.length];
		for (int i = 0; i < a.length; i++)
			c[i] = a[i] == 0 ? 1 : 0;
		return c;
	}

	public static double[] ceil(double[] a) {
		double[] c = new double[a.length];
		for (int i = 0; i < a.length; i++)
			c[i] = Math.ceil(a[i]);
		return c;
	}

	public static double[] factorial(double[] a) {
		double[] c = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			c[i] = 1;
			for (int j = 1; j <= a[i]; j++) {
				c[i] *= j;
			}
		}
		return c;
	}
}
