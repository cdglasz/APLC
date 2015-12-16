import java.util.*;
public class functions {
   private static String sep = "________________________________________________________________________________";
   private static APLTensor _V__R_;
   private static APLTensor _V__terms_;
   public static void main(String[] args) {
      APLTensor _T__left_, _T__right_;
      _T__right_ = new APLTensor(4.0);
      APLTensor _T__var_4_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_4_;
      _T__right_ = APLOps.exec(new _F__vol_(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(5.0);
      _T__right_ = APLOps.exec(new APLOps.indices(), _T__right_);
      APLTensor _T__var_6_ = _T__right_;
      _T__right_ = new APLTensor(5.0);
      _T__right_ = APLOps.exec(new APLOps.indices(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_6_;
      _T__right_ = APLOps.exec(new APLOps.null_tie(new _F__vol_()), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(20.0);
      _T__right_ = APLOps.exec(new APLOps.indices(), _T__right_);
      APLTensor _T__var_12_ = _T__right_;
      _T__right_ = new APLTensor(0.005, 0.008, 0.012, 0.02, 0.05);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_12_;
      _T__right_ = APLOps.exec(new _F__interest_(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(31.0);
      _T__right_ = APLOps.exec(new APLOps.indices(), _T__right_);
      APLTensor _T__var_18_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_18_;
      _T__right_ = APLOps.exec(new _F__base_(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(42.0, -12.0, 67.0);
      APLTensor _T__var_23_ = _T__right_;
      _T__right_ = new APLTensor(0.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_23_;
      _T__right_ = APLOps.exec(new _F__dist_(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(75.0);
      _T__right_ = APLOps.exec(new _F__primes_1_(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(5.0, 3.0, 7.0, 8.0, 2.0, 3.0);
      APLTensor _T__var_35_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_35_;
      _T__right_ = APLOps.exec(new _F__poly_(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
   }

   // Function associated with area
   static class _F__area_ extends APLOps.Operation {
      public String symbol() { return "area"; }
      public APLTensor exec(APLTensor _A__left_, APLTensor _A__right_){
         APLTensor _T__left_, _T__right_;
         _T__right_ = new APLTensor(2.0);
         APLTensor _T__var_0_ = _T__right_;
         _T__right_ = _A__right_;
         _T__left_ = _T__right_;
         _T__right_ = _T__var_0_;
         _T__right_ = APLOps.exec(new APLOps.pow(), _T__left_, _T__right_);
         APLTensor _T__var_1_ = _T__right_;
         _T__right_ = new APLTensor(1.0);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_1_;
         _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
         _T__right_ = APLOps.exec(new APLOps.pi(), _T__right_);
         return _T__right_;
      }
   }

   // Function associated with vol
   static class _F__vol_ extends APLOps.Operation {
      public String symbol() { return "vol"; }
      public APLTensor exec(APLTensor _A__left_, APLTensor _A__right_){
         APLTensor _T__left_, _T__right_;
         _T__right_ = _A__left_;
         _T__right_ = APLOps.exec(new _F__area_(), _T__right_);
         APLTensor _T__var_2_ = _T__right_;
         _T__right_ = _A__right_;
         _T__left_ = _T__right_;
         _T__right_ = _T__var_2_;
         _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
         return _T__right_;
      }
   }

   // Function associated with interest
   static class _F__interest_ extends APLOps.Operation {
      public String symbol() { return "interest"; }
      public APLTensor exec(APLTensor _A__left_, APLTensor _A__right_){
         APLTensor _T__left_, _T__right_;
         _T__right_ = _A__right_;
         APLTensor _T__var_8_ = _T__right_;
         _T__right_ = _A__left_;
         APLTensor _T__var_7_ = _T__right_;
         _T__right_ = new APLTensor(1.0);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_7_;
         _T__right_ = APLOps.exec(new APLOps.add(), _T__left_, _T__right_);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_8_;
         _T__right_ = APLOps.exec(new APLOps.null_tie(new APLOps.pow()), _T__left_, _T__right_);
         APLTensor _T__var_9_ = _T__right_;
         _T__right_ = new APLTensor(10000.0);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_9_;
         _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
         APLTensor _T__var_10_ = _T__right_;
         _T__right_ = new APLTensor(2.0);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_10_;
         _T__right_ = APLOps.exec(new APLOps.format(), _T__left_, _T__right_);
         _T__right_ = APLOps.exec(new APLOps.trans(), _T__right_);
         return _T__right_;
      }
   }

   // Function associated with base
   static class _F__base_ extends APLOps.Operation {
      public String symbol() { return "base"; }
      public APLTensor exec(APLTensor _A__left_, APLTensor _A__right_){
         APLTensor _T__left_, _T__right_;
         _T__right_ = _A__right_;
         APLTensor _T__var_16_ = _T__right_;
         _T__right_ = _A__left_;
         APLTensor _T__var_15_ = _T__right_;
         _T__right_ = _A__right_;
         _T__right_ = APLOps.exec(new APLOps.reduce(new APLOps.max()), _T__right_);
         APLTensor _T__var_13_ = _T__right_;
         _T__right_ = _A__left_;
         _T__left_ = _T__right_;
         _T__right_ = _T__var_13_;
         _T__right_ = APLOps.exec(new APLOps.log(), _T__left_, _T__right_);
         _T__right_ = APLOps.exec(new APLOps.floor(), _T__right_);
         APLTensor _T__var_14_ = _T__right_;
         _T__right_ = new APLTensor(1.0);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_14_;
         _T__right_ = APLOps.exec(new APLOps.add(), _T__left_, _T__right_);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_15_;
         _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_16_;
         _T__right_ = APLOps.exec(new APLOps.antibase(), _T__left_, _T__right_);
         _T__right_ = APLOps.exec(new APLOps.trans(), _T__right_);
         return _T__right_;
      }
   }

   // Function associated with dist
   static class _F__dist_ extends APLOps.Operation {
      public String symbol() { return "dist"; }
      public APLTensor exec(APLTensor _A__left_, APLTensor _A__right_){
         APLTensor _T__left_, _T__right_;
         _T__right_ = new APLTensor(2.0);
         _T__right_ = APLOps.exec(new APLOps.reciprocal(), _T__right_);
         APLTensor _T__var_21_ = _T__right_;
         _T__right_ = new APLTensor(2.0);
         APLTensor _T__var_20_ = _T__right_;
         _T__right_ = _A__right_;
         APLTensor _T__var_19_ = _T__right_;
         _T__right_ = _A__left_;
         _T__left_ = _T__right_;
         _T__right_ = _T__var_19_;
         _T__right_ = APLOps.exec(new APLOps.sub(), _T__left_, _T__right_);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_20_;
         _T__right_ = APLOps.exec(new APLOps.pow(), _T__left_, _T__right_);
         _T__right_ = APLOps.exec(new APLOps.reduce(new APLOps.add()), _T__right_);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_21_;
         _T__right_ = APLOps.exec(new APLOps.pow(), _T__left_, _T__right_);
         return _T__right_;
      }
   }

   // Function associated with primes
   static class _F__primes_ extends APLOps.Operation {
      public String symbol() { return "primes"; }
      public APLTensor exec(APLTensor _A__left_, APLTensor _A__right_){
         APLTensor _T__left_, _T__right_;
         _T__right_ = _A__right_;
         _T__right_ = APLOps.exec(new APLOps.indices(), _T__right_);
         _V__R_ = _T__right_;
         APLTensor _T__var_26_ = _T__right_;
         _T__right_ = _V__R_;
         APLTensor _T__var_24_ = _T__right_;
         _T__right_ = _V__R_;
         _T__left_ = _T__right_;
         _T__right_ = _T__var_24_;
         _T__right_ = APLOps.exec(new APLOps.null_tie(new APLOps.mul()), _T__left_, _T__right_);
         APLTensor _T__var_25_ = _T__right_;
         _T__right_ = _V__R_;
         _T__left_ = _T__right_;
         _T__right_ = _T__var_25_;
         _T__right_ = APLOps.exec(new APLOps.contains(), _T__left_, _T__right_);
         _T__right_ = APLOps.exec(new APLOps.not(), _T__right_);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_26_;
         _T__right_ = APLOps.exec(new APLOps.compress(), _T__left_, _T__right_);
         return _T__right_;
      }
   }

   // Function associated with primes
   static class _F__primes_1_ extends APLOps.Operation {
      public String symbol() { return "primes"; }
      public APLTensor exec(APLTensor _A__left_, APLTensor _A__right_){
         APLTensor _T__left_, _T__right_;
         _T__right_ = _A__right_;
         _T__right_ = APLOps.exec(new APLOps.indices(), _T__right_);
         _V__R_ = _T__right_;
         APLTensor _T__var_30_ = _T__right_;
         _T__right_ = _V__R_;
         APLTensor _T__var_27_ = _T__right_;
         _T__right_ = _V__R_;
         _T__left_ = _T__right_;
         _T__right_ = _T__var_27_;
         _T__right_ = APLOps.exec(new APLOps.null_tie(new APLOps.mod()), _T__left_, _T__right_);
         APLTensor _T__var_28_ = _T__right_;
         _T__right_ = new APLTensor(0.0);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_28_;
         _T__right_ = APLOps.exec(new APLOps.equ(), _T__left_, _T__right_);
         _T__right_ = APLOps.exec(new APLOps.reduce1(new APLOps.add()), _T__right_);
         APLTensor _T__var_29_ = _T__right_;
         _T__right_ = new APLTensor(2.0);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_29_;
         _T__right_ = APLOps.exec(new APLOps.equ(), _T__left_, _T__right_);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_30_;
         _T__right_ = APLOps.exec(new APLOps.compress(), _T__left_, _T__right_);
         return _T__right_;
      }
   }

   // Function associated with exponents
   static class _F__exponents_ extends APLOps.Operation {
      public String symbol() { return "exponents"; }
      public APLTensor exec(APLTensor _A__left_, APLTensor _A__right_){
         APLTensor _T__left_, _T__right_;
         _T__right_ = _A__right_;
         _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
         _T__right_ = APLOps.exec(new APLOps.indices(), _T__right_);
         APLTensor _T__var_31_ = _T__right_;
         _T__right_ = new APLTensor(-1.0);
         _T__left_ = _T__right_;
         _T__right_ = _T__var_31_;
         _T__right_ = APLOps.exec(new APLOps.add(), _T__left_, _T__right_);
         return _T__right_;
      }
   }

   // Function associated with poly
   static class _F__poly_ extends APLOps.Operation {
      public String symbol() { return "poly"; }
      public APLTensor exec(APLTensor _A__left_, APLTensor _A__right_){
         APLTensor _FV__out_ = null;
         APLTensor _FV__left_ = _A__left_;
         APLTensor _FV__right_ = _A__right_;
         APLTensor _T__left_, _T__right_;
         _T__right_ = _FV__right_;
         _T__right_ = APLOps.exec(new _F__exponents_(), _T__right_);
         APLTensor _T__var_32_ = _T__right_;
         _T__right_ = _FV__left_;
         _T__left_ = _T__right_;
         _T__right_ = _T__var_32_;
         _T__right_ = APLOps.exec(new APLOps.pow(), _T__left_, _T__right_);
         APLTensor _T__var_33_ = _T__right_;
         _T__right_ = _FV__right_;
         _T__left_ = _T__right_;
         _T__right_ = _T__var_33_;
         _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
         _V__terms_ = _T__right_;
         _T__right_ = _V__terms_;
         _T__right_ = APLOps.exec(new APLOps.reduce(new APLOps.add()), _T__right_);
         _FV__out_ = _T__right_;
         if (_FV__out_ == null)
            APLOps.log("DYADIC OPERATOR poly RETURNED NULL");         return _FV__out_;
      }
      }
}