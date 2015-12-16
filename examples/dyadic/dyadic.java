import java.util.*;
public class dyadic {
   private static String sep = "________________________________________________________________________________";
   private static APLTensor _V__a1_;
   private static APLTensor _V__b1_;
   private static APLTensor _V__c1_;
   private static APLTensor _V__a2_;
   private static APLTensor _V__b2_;
   private static APLTensor _V__c2_;
   private static APLTensor _V__fa1_;
   private static APLTensor _V__ba1_;
   private static APLTensor _V__fb1_;
   private static APLTensor _V__bb1_;
   private static APLTensor _V__fc1_;
   private static APLTensor _V__bc1_;
   private static APLTensor _V__fa2_;
   private static APLTensor _V__ba2_;
   private static APLTensor _V__fb2_;
   private static APLTensor _V__bb2_;
   private static APLTensor _V__fc2_;
   private static APLTensor _V__bc2_;
   private static APLTensor _V__i_;
   private static APLTensor _V__j_;
   private static APLTensor _V__k_;
   private static APLTensor _V__sep_;
   public static void main(String[] args) {
      APLTensor _T__left_, _T__right_;
      _T__right_ = new APLTensor(1.0, 2.0, -1.0, -2.0, 3.0);
      _V__a1_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(-2.0, 4.0, -3.0, -4.0, 6.0, 2.0, -1.0, -5.0, 7.0, 1.0, 3.0, 5.0);
      APLTensor _T__var_0_ = _T__right_;
      _T__right_ = new APLTensor(3.0, 4.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_0_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _V__b1_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(6.0, 1.0, -13.0, -28.0, -3.0, -1.0, -22.0, 20.0, 16.0, 10.0, -10.0, -27.0, -12.0, 26.0, 5.0, -21.0, 29.0, -2.0, 4.0, 25.0, 24.0, 21.0, -18.0, -16.0, -9.0, 12.0, 3.0, -26.0, 14.0, 19.0, 18.0, -29.0, -17.0, -25.0, -11.0, 13.0, 30.0, -6.0, 8.0, -24.0, 27.0, 9.0, -8.0, -20.0, -5.0, 2.0, -19.0, 15.0, 23.0, -4.0, 31.0, -23.0, 17.0, -14.0, -7.0, 22.0, 11.0, 7.0, -15.0, 28.0);
      APLTensor _T__var_1_ = _T__right_;
      _T__right_ = new APLTensor(3.0, 4.0, 5.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_1_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _V__c1_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(-2.0, 2.0, 1.0, 3.0, -1.0);
      _V__a2_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(5.0, -3.0, -2.0, 6.0, -4.0, -5.0, -6.0, 1.0, 4.0, 7.0, 2.0, 3.0);
      APLTensor _T__var_2_ = _T__right_;
      _T__right_ = new APLTensor(3.0, 4.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_2_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _V__b2_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(3.0, -19.0, -15.0, 9.0, -1.0, 12.0, -23.0, -2.0, 7.0, 16.0, 25.0, 19.0, -17.0, 24.0, -9.0, -29.0, 1.0, 27.0, 10.0, -21.0, -4.0, -20.0, 18.0, 14.0, -18.0, 11.0, -27.0, -24.0, 20.0, -28.0, 2.0, 17.0, -30.0, 30.0, 28.0, -6.0, -16.0, 23.0, 4.0, -13.0, 5.0, 8.0, -3.0, -12.0, -8.0, 6.0, -25.0, 15.0, 29.0, -14.0, -10.0, 31.0, 21.0, 26.0, -7.0, -26.0, -11.0, 13.0, -22.0, 22.0);
      APLTensor _T__var_3_ = _T__right_;
      _T__right_ = new APLTensor(3.0, 4.0, 5.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_3_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _V__c2_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a1_;
      APLTensor _T__var_4_ = _T__right_;
      _T__right_ = new APLTensor(3.0);
      _T__right_ = APLOps.exec(new APLOps.reciprocal(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.pi(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_4_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      _V__fa1_ = _T__right_;
      _T__right_ = APLOps.exec(new APLOps.ceil(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_5_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_5_;
      _T__right_ = APLOps.exec(new APLOps.mod(), _T__left_, _T__right_);
      _V__ba1_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b1_;
      APLTensor _T__var_6_ = _T__right_;
      _T__right_ = new APLTensor(3.0);
      _T__right_ = APLOps.exec(new APLOps.reciprocal(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.pi(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_6_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      _V__fb1_ = _T__right_;
      _T__right_ = APLOps.exec(new APLOps.ceil(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_7_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_7_;
      _T__right_ = APLOps.exec(new APLOps.mod(), _T__left_, _T__right_);
      _V__bb1_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c1_;
      APLTensor _T__var_8_ = _T__right_;
      _T__right_ = new APLTensor(3.0);
      _T__right_ = APLOps.exec(new APLOps.reciprocal(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.pi(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_8_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      _V__fc1_ = _T__right_;
      _T__right_ = APLOps.exec(new APLOps.ceil(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_9_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_9_;
      _T__right_ = APLOps.exec(new APLOps.mod(), _T__left_, _T__right_);
      _V__bc1_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_10_ = _T__right_;
      _T__right_ = new APLTensor(3.0);
      _T__right_ = APLOps.exec(new APLOps.reciprocal(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.pi(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_10_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      _V__fa2_ = _T__right_;
      _T__right_ = APLOps.exec(new APLOps.ceil(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_11_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_11_;
      _T__right_ = APLOps.exec(new APLOps.mod(), _T__left_, _T__right_);
      _V__ba2_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_12_ = _T__right_;
      _T__right_ = new APLTensor(3.0);
      _T__right_ = APLOps.exec(new APLOps.reciprocal(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.pi(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_12_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      _V__fb2_ = _T__right_;
      _T__right_ = APLOps.exec(new APLOps.ceil(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_13_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_13_;
      _T__right_ = APLOps.exec(new APLOps.mod(), _T__left_, _T__right_);
      _V__bb2_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_14_ = _T__right_;
      _T__right_ = new APLTensor(3.0);
      _T__right_ = APLOps.exec(new APLOps.reciprocal(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.pi(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_14_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      _V__fc2_ = _T__right_;
      _T__right_ = APLOps.exec(new APLOps.ceil(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_15_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_15_;
      _T__right_ = APLOps.exec(new APLOps.mod(), _T__left_, _T__right_);
      _V__bc2_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(3.0);
      _V__i_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(-2.0, 1.0);
      _V__j_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(2.0, 2.0, -1.0);
      _V__k_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(88888888888888.0);
      APLTensor _T__var_16_ = _T__right_;
      _T__right_ = new APLTensor(5.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_16_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _V__sep_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_17_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_17_;
      _T__right_ = APLOps.exec(new APLOps.contains(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_18_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_18_;
      _T__right_ = APLOps.exec(new APLOps.contains(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_19_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_19_;
      _T__right_ = APLOps.exec(new APLOps.contains(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_20_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_20_;
      _T__right_ = APLOps.exec(new APLOps.less(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_21_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_21_;
      _T__right_ = APLOps.exec(new APLOps.less(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_22_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_22_;
      _T__right_ = APLOps.exec(new APLOps.less(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_23_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_23_;
      _T__right_ = APLOps.exec(new APLOps.leq(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_24_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_24_;
      _T__right_ = APLOps.exec(new APLOps.leq(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_25_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_25_;
      _T__right_ = APLOps.exec(new APLOps.leq(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_26_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_26_;
      _T__right_ = APLOps.exec(new APLOps.equ(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_27_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_27_;
      _T__right_ = APLOps.exec(new APLOps.equ(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_28_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_28_;
      _T__right_ = APLOps.exec(new APLOps.equ(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a1_;
      APLTensor _T__var_29_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_29_;
      _T__right_ = APLOps.exec(new APLOps.equivilent(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_30_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_30_;
      _T__right_ = APLOps.exec(new APLOps.equivilent(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_31_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_31_;
      _T__right_ = APLOps.exec(new APLOps.equivilent(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_32_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_32_;
      _T__right_ = APLOps.exec(new APLOps.greq(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_33_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_33_;
      _T__right_ = APLOps.exec(new APLOps.greq(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_34_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_34_;
      _T__right_ = APLOps.exec(new APLOps.greq(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_35_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_35_;
      _T__right_ = APLOps.exec(new APLOps.greater(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_36_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_36_;
      _T__right_ = APLOps.exec(new APLOps.greater(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_37_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_37_;
      _T__right_ = APLOps.exec(new APLOps.greater(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_38_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_38_;
      _T__right_ = APLOps.exec(new APLOps.neq(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_39_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_39_;
      _T__right_ = APLOps.exec(new APLOps.neq(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_40_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_40_;
      _T__right_ = APLOps.exec(new APLOps.neq(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_41_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_41_;
      _T__right_ = APLOps.exec(new APLOps.gcd(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_42_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_42_;
      _T__right_ = APLOps.exec(new APLOps.gcd(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_43_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_43_;
      _T__right_ = APLOps.exec(new APLOps.gcd(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_44_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_44_;
      _T__right_ = APLOps.exec(new APLOps.lcm(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_45_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_45_;
      _T__right_ = APLOps.exec(new APLOps.lcm(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_46_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_46_;
      _T__right_ = APLOps.exec(new APLOps.lcm(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__ba2_;
      APLTensor _T__var_47_ = _T__right_;
      _T__right_ = _V__ba1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_47_;
      _T__right_ = APLOps.exec(new APLOps.nor(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__bb2_;
      APLTensor _T__var_48_ = _T__right_;
      _T__right_ = _V__bb1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_48_;
      _T__right_ = APLOps.exec(new APLOps.nor(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__bc2_;
      APLTensor _T__var_49_ = _T__right_;
      _T__right_ = _V__bc1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_49_;
      _T__right_ = APLOps.exec(new APLOps.nor(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__ba2_;
      APLTensor _T__var_50_ = _T__right_;
      _T__right_ = _V__ba1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_50_;
      _T__right_ = APLOps.exec(new APLOps.nand(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__bb2_;
      APLTensor _T__var_51_ = _T__right_;
      _T__right_ = _V__bb1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_51_;
      _T__right_ = APLOps.exec(new APLOps.nand(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__bc2_;
      APLTensor _T__var_52_ = _T__right_;
      _T__right_ = _V__bc1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_52_;
      _T__right_ = APLOps.exec(new APLOps.nand(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_53_ = _T__right_;
      _T__right_ = _V__i_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_53_;
      _T__right_ = APLOps.exec(new APLOps.take(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_54_ = _T__right_;
      _T__right_ = _V__j_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_54_;
      _T__right_ = APLOps.exec(new APLOps.take(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_55_ = _T__right_;
      _T__right_ = _V__k_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_55_;
      _T__right_ = APLOps.exec(new APLOps.take(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_56_ = _T__right_;
      _T__right_ = _V__i_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_56_;
      _T__right_ = APLOps.exec(new APLOps.drop(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_57_ = _T__right_;
      _T__right_ = _V__j_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_57_;
      _T__right_ = APLOps.exec(new APLOps.drop(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_58_ = _T__right_;
      _T__right_ = _V__k_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_58_;
      _T__right_ = APLOps.exec(new APLOps.drop(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_60_ = _T__right_;
      _T__right_ = new APLTensor(10.0);
      APLTensor _T__var_59_ = _T__right_;
      _T__right_ = _V__a2_;
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.head(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_59_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_60_;
      _T__right_ = APLOps.exec(new APLOps.base(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_62_ = _T__right_;
      _T__right_ = new APLTensor(10.0);
      APLTensor _T__var_61_ = _T__right_;
      _T__right_ = _V__b2_;
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.head(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_61_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_62_;
      _T__right_ = APLOps.exec(new APLOps.base(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_64_ = _T__right_;
      _T__right_ = new APLTensor(10.0);
      APLTensor _T__var_63_ = _T__right_;
      _T__right_ = _V__c2_;
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.head(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_63_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_64_;
      _T__right_ = APLOps.exec(new APLOps.base(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_66_ = _T__right_;
      _T__right_ = new APLTensor(10.0);
      APLTensor _T__var_65_ = _T__right_;
      _T__right_ = _V__a2_;
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.head(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_65_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_66_;
      _T__right_ = APLOps.exec(new APLOps.antibase(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_68_ = _T__right_;
      _T__right_ = new APLTensor(10.0);
      APLTensor _T__var_67_ = _T__right_;
      _T__right_ = _V__b2_;
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.head(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_67_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_68_;
      _T__right_ = APLOps.exec(new APLOps.antibase(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_70_ = _T__right_;
      _T__right_ = new APLTensor(10.0);
      APLTensor _T__var_69_ = _T__right_;
      _T__right_ = _V__c2_;
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.head(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_69_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_70_;
      _T__right_ = APLOps.exec(new APLOps.antibase(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a1_;
      _T__right_ = APLOps.exec(new APLOps.ravel(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      APLTensor _T__var_71_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__right_ = APLOps.exec(new APLOps.ravel(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_71_;
      _T__right_ = APLOps.exec(new APLOps.deal(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b1_;
      _T__right_ = APLOps.exec(new APLOps.ravel(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      APLTensor _T__var_72_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__right_ = APLOps.exec(new APLOps.ravel(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_72_;
      _T__right_ = APLOps.exec(new APLOps.deal(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c1_;
      _T__right_ = APLOps.exec(new APLOps.ravel(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      APLTensor _T__var_73_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__right_ = APLOps.exec(new APLOps.ravel(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_73_;
      _T__right_ = APLOps.exec(new APLOps.deal(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_74_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_74_;
      _T__right_ = APLOps.exec(new APLOps.max(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_75_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_75_;
      _T__right_ = APLOps.exec(new APLOps.max(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_76_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_76_;
      _T__right_ = APLOps.exec(new APLOps.max(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_77_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_77_;
      _T__right_ = APLOps.exec(new APLOps.min(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_78_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_78_;
      _T__right_ = APLOps.exec(new APLOps.min(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_79_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_79_;
      _T__right_ = APLOps.exec(new APLOps.min(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_80_ = _T__right_;
      _T__right_ = _V__i_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_80_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_81_ = _T__right_;
      _T__right_ = _V__j_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_81_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_82_ = _T__right_;
      _T__right_ = _V__k_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_82_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_83_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_83_;
      _T__right_ = APLOps.exec(new APLOps.mod(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_84_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_84_;
      _T__right_ = APLOps.exec(new APLOps.mod(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_85_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_85_;
      _T__right_ = APLOps.exec(new APLOps.mod(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_86_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_86_;
      _T__right_ = APLOps.exec(new APLOps.indexof(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_87_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_87_;
      _T__right_ = APLOps.exec(new APLOps.indexof(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_88_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_88_;
      _T__right_ = APLOps.exec(new APLOps.indexof(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_89_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_89_;
      _T__right_ = APLOps.exec(new APLOps.pow(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_90_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_90_;
      _T__right_ = APLOps.exec(new APLOps.pow(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_91_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_91_;
      _T__right_ = APLOps.exec(new APLOps.pow(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_92_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_92_;
      _T__right_ = APLOps.exec(new APLOps.sub(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_93_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_93_;
      _T__right_ = APLOps.exec(new APLOps.sub(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_94_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_94_;
      _T__right_ = APLOps.exec(new APLOps.sub(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_95_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_95_;
      _T__right_ = APLOps.exec(new APLOps.add(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_96_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_96_;
      _T__right_ = APLOps.exec(new APLOps.add(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_97_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_97_;
      _T__right_ = APLOps.exec(new APLOps.add(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_98_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_98_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_99_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_99_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_100_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_100_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_101_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_101_;
      _T__right_ = APLOps.exec(new APLOps.div(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_102_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_102_;
      _T__right_ = APLOps.exec(new APLOps.div(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_103_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_103_;
      _T__right_ = APLOps.exec(new APLOps.div(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_104_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_104_;
      _T__right_ = APLOps.exec(new APLOps.concat(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_105_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_105_;
      _T__right_ = APLOps.exec(new APLOps.concat(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_106_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_106_;
      _T__right_ = APLOps.exec(new APLOps.concat(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_107_ = _T__right_;
      _T__right_ = new APLTensor(1.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_107_;
      _T__right_ = APLOps.exec(new APLOps.trig(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_108_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_108_;
      _T__right_ = APLOps.exec(new APLOps.trig(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_109_ = _T__right_;
      _T__right_ = new APLTensor(4.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_109_;
      _T__right_ = APLOps.exec(new APLOps.trig(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_110_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_110_;
      _T__right_ = APLOps.exec(new APLOps.log(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_111_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_111_;
      _T__right_ = APLOps.exec(new APLOps.log(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_112_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_112_;
      _T__right_ = APLOps.exec(new APLOps.log(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_113_ = _T__right_;
      _T__right_ = _V__i_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_113_;
      _T__right_ = APLOps.exec(new APLOps.permute(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_114_ = _T__right_;
      _T__right_ = _V__j_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_114_;
      _T__right_ = APLOps.exec(new APLOps.permute(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_115_ = _T__right_;
      _T__right_ = _V__k_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_115_;
      _T__right_ = APLOps.exec(new APLOps.permute(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_116_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_116_;
      _T__right_ = APLOps.exec(new APLOps.combinations(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_117_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_117_;
      _T__right_ = APLOps.exec(new APLOps.combinations(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_118_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_118_;
      _T__right_ = APLOps.exec(new APLOps.combinations(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__fa2_;
      APLTensor _T__var_119_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_119_;
      _T__right_ = APLOps.exec(new APLOps.format(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__fb2_;
      APLTensor _T__var_120_ = _T__right_;
      _T__right_ = new APLTensor(3.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_120_;
      _T__right_ = APLOps.exec(new APLOps.format(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__fc2_;
      APLTensor _T__var_121_ = _T__right_;
      _T__right_ = new APLTensor(6.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_121_;
      _T__right_ = APLOps.exec(new APLOps.format(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__sep_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a2_;
      APLTensor _T__var_124_ = _T__right_;
      _T__right_ = _V__a1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_122_ = _T__right_;
      _T__right_ = new APLTensor(-1.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_122_;
      _T__right_ = APLOps.exec(new APLOps.add(), _T__left_, _T__right_);
      APLTensor _T__var_123_ = _T__right_;
      _T__right_ = _V__a2_;
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_123_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_124_;
      _T__right_ = APLOps.exec(new APLOps.compress(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b2_;
      APLTensor _T__var_127_ = _T__right_;
      _T__right_ = _V__b1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_125_ = _T__right_;
      _T__right_ = new APLTensor(-1.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_125_;
      _T__right_ = APLOps.exec(new APLOps.add(), _T__left_, _T__right_);
      APLTensor _T__var_126_ = _T__right_;
      _T__right_ = _V__b2_;
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_126_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_127_;
      _T__right_ = APLOps.exec(new APLOps.compress(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c2_;
      APLTensor _T__var_130_ = _T__right_;
      _T__right_ = _V__c1_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_128_ = _T__right_;
      _T__right_ = new APLTensor(-1.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_128_;
      _T__right_ = APLOps.exec(new APLOps.add(), _T__left_, _T__right_);
      APLTensor _T__var_129_ = _T__right_;
      _T__right_ = _V__c2_;
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_129_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_130_;
      _T__right_ = APLOps.exec(new APLOps.compress(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
   }
}