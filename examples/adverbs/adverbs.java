import java.util.*;
public class adverbs {
   private static String sep = "________________________________________________________________________________";
   private static APLTensor _V__data_;
   private static APLTensor _V__a_;
   private static APLTensor _V__b_;
   private static APLTensor _V__c_;
   public static void main(String[] args) {
      APLTensor _T__left_, _T__right_;
      _T__right_ = new APLTensor(1000.0);
      _T__right_ = APLOps.exec(new APLOps.indices(), _T__right_);
      _V__data_ = _T__right_;
      _T__right_ = _V__data_;
      APLTensor _T__var_0_ = _T__right_;
      _T__right_ = new APLTensor(10.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_0_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _V__a_ = _T__right_;
      _T__right_ = _V__data_;
      APLTensor _T__var_1_ = _T__right_;
      _T__right_ = new APLTensor(4.0, 5.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_1_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _V__b_ = _T__right_;
      _T__right_ = _V__data_;
      APLTensor _T__var_2_ = _T__right_;
      _T__right_ = new APLTensor(3.0, 4.0, 5.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_2_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _V__c_ = _T__right_;
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.reduce(new APLOps.add()), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.reduce(new APLOps.add()), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.reduce(new APLOps.add()), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.reduce1(new APLOps.add()), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.reduce1(new APLOps.add()), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.reduce1(new APLOps.add()), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.scan(new APLOps.add()), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.scan(new APLOps.add()), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.scan(new APLOps.add()), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.scan1(new APLOps.add()), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.scan1(new APLOps.add()), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.scan1(new APLOps.add()), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
   }
}