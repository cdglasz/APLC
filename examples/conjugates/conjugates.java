import java.util.*;
public class conjugates {
   private static String sep = "________________________________________________________________________________";
   private static APLTensor _V__a_;
   private static APLTensor _V__b_;
   private static APLTensor _V__d_;
   private static APLTensor _V__c_;
   public static void main(String[] args) {
      APLTensor _T__left_, _T__right_;
      _T__right_ = new APLTensor(1.0, 4.0, 2.0, 6.0, 7.0, 8.0, 3.0, 9.0, 5.0);
      _V__a_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(4.0, 3.0, 2.0, 5.0, 7.0, 9.0, 8.0, 1.0, 6.0);
      _V__b_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(25.0);
      _T__right_ = APLOps.exec(new APLOps.indices(), _T__right_);
      APLTensor _T__var_0_ = _T__right_;
      _T__right_ = new APLTensor(5.0, 5.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_0_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _V__d_ = _T__right_;
      _V__c_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      APLTensor _T__var_1_ = _T__right_;
      _T__right_ = _V__a_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_1_;
      _T__right_ = APLOps.exec(new APLOps.tie(new APLOps.add(),new APLOps.mul()), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__d_;
      APLTensor _T__var_2_ = _T__right_;
      _T__right_ = _V__c_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_2_;
      _T__right_ = APLOps.exec(new APLOps.tie(new APLOps.add(),new APLOps.mul()), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      APLTensor _T__var_3_ = _T__right_;
      _T__right_ = _V__a_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_3_;
      _T__right_ = APLOps.exec(new APLOps.tie(new APLOps.add(),new APLOps.equ()), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(25.0);
      APLTensor _T__var_4_ = _T__right_;
      _T__right_ = new APLTensor(25.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_4_;
      _T__right_ = APLOps.exec(new APLOps.deal(), _T__left_, _T__right_);
      APLTensor _T__var_5_ = _T__right_;
      _T__right_ = new APLTensor(5.0, 5.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_5_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      APLTensor _T__var_7_ = _T__right_;
      _T__right_ = new APLTensor(5.0);
      _T__right_ = APLOps.exec(new APLOps.indices(), _T__right_);
      APLTensor _T__var_6_ = _T__right_;
      _T__right_ = new APLTensor(5.0);
      _T__right_ = APLOps.exec(new APLOps.indices(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_6_;
      _T__right_ = APLOps.exec(new APLOps.null_tie(new APLOps.equ()), _T__left_, _T__right_);
      _T__right_ = APLOps.exec(new APLOps.not(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_7_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      _V__c_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      APLTensor _T__var_8_ = _T__right_;
      _T__right_ = _V__c_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_8_;
      _T__right_ = APLOps.exec(new APLOps.tie(new APLOps.min(),new APLOps.add()), _T__left_, _T__right_);
      APLTensor _T__var_9_ = _T__right_;
      _T__right_ = _V__c_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_9_;
      _T__right_ = APLOps.exec(new APLOps.tie(new APLOps.min(),new APLOps.add()), _T__left_, _T__right_);
      APLTensor _T__var_10_ = _T__right_;
      _T__right_ = _V__c_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_10_;
      _T__right_ = APLOps.exec(new APLOps.tie(new APLOps.min(),new APLOps.add()), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      APLTensor _T__var_11_ = _T__right_;
      _T__right_ = _V__a_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_11_;
      _T__right_ = APLOps.exec(new APLOps.null_tie(new APLOps.mul()), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a_;
      APLTensor _T__var_12_ = _T__right_;
      _T__right_ = _V__a_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_12_;
      _T__right_ = APLOps.exec(new APLOps.null_tie(new APLOps.equ()), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(10.0);
      _T__right_ = APLOps.exec(new APLOps.indices(), _T__right_);
      _V__a_ = _T__right_;
      APLTensor _T__var_13_ = _T__right_;
      _T__right_ = _V__a_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_13_;
      _T__right_ = APLOps.exec(new APLOps.null_tie(new APLOps.mul()), _T__left_, _T__right_);
      APLTensor _T__var_15_ = _T__right_;
      _T__right_ = _V__a_;
      APLTensor _T__var_14_ = _T__right_;
      _T__right_ = _V__a_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_14_;
      _T__right_ = APLOps.exec(new APLOps.null_tie(new APLOps.equ()), _T__left_, _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_15_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      _T__right_ = APLOps.exec(new APLOps.reduce1(new APLOps.add()), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
   }
}