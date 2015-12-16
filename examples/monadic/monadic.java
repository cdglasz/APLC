import java.util.*;
public class monadic {
   private static String sep = "________________________________________________________________________________";
   private static APLTensor _V__a_;
   private static APLTensor _V__b_;
   private static APLTensor _V__c_;
   private static APLTensor _V__fa_;
   private static APLTensor _V__ba_;
   private static APLTensor _V__fb_;
   private static APLTensor _V__bb_;
   private static APLTensor _V__fc_;
   private static APLTensor _V__bc_;
   private static APLTensor _V__sep_;
   public static void main(String[] args) {
      APLTensor _T__left_, _T__right_;
      _T__right_ = new APLTensor(1.0, -2.0, -1.0, 2.0, 3.0);
      _V__a_ = _T__right_;
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
      _V__b_ = _T__right_;
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
      _V__c_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a_;
      APLTensor _T__var_2_ = _T__right_;
      _T__right_ = new APLTensor(3.0);
      _T__right_ = APLOps.exec(new APLOps.reciprocal(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.pi(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_2_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      _V__fa_ = _T__right_;
      _T__right_ = APLOps.exec(new APLOps.ceil(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_3_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_3_;
      _T__right_ = APLOps.exec(new APLOps.mod(), _T__left_, _T__right_);
      _V__ba_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      APLTensor _T__var_4_ = _T__right_;
      _T__right_ = new APLTensor(3.0);
      _T__right_ = APLOps.exec(new APLOps.reciprocal(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.pi(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_4_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      _V__fb_ = _T__right_;
      _T__right_ = APLOps.exec(new APLOps.ceil(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_5_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_5_;
      _T__right_ = APLOps.exec(new APLOps.mod(), _T__left_, _T__right_);
      _V__bb_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      APLTensor _T__var_6_ = _T__right_;
      _T__right_ = new APLTensor(3.0);
      _T__right_ = APLOps.exec(new APLOps.reciprocal(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.pi(), _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_6_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      _V__fc_ = _T__right_;
      _T__right_ = APLOps.exec(new APLOps.ceil(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      APLTensor _T__var_7_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_7_;
      _T__right_ = APLOps.exec(new APLOps.mod(), _T__left_, _T__right_);
      _V__bc_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(88888888888888.0);
      APLTensor _T__var_8_ = _T__right_;
      _T__right_ = new APLTensor(5.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_8_;
      _T__right_ = APLOps.exec(new APLOps.reshape(), _T__left_, _T__right_);
      _V__sep_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.clone(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.clone(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.clone(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.neg(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.neg(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.neg(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.signum(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.signum(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.signum(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.reciprocal(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.reciprocal(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.reciprocal(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.exp(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.exp(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.exp(), _T__right_);
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
      _T__right_ = _V__ba_;
      _T__right_ = APLOps.exec(new APLOps.not(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__bb_;
      _T__right_ = APLOps.exec(new APLOps.not(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__bc_;
      _T__right_ = APLOps.exec(new APLOps.not(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.sort(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.sort(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.sort(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.sortdown(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.sortdown(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.sortdown(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.roll(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.roll(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.roll(), _T__right_);
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
      _T__right_ = _V__fa_;
      _T__right_ = APLOps.exec(new APLOps.ceil(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__fb_;
      _T__right_ = APLOps.exec(new APLOps.ceil(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__fc_;
      _T__right_ = APLOps.exec(new APLOps.ceil(), _T__right_);
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
      _T__right_ = _V__fa_;
      _T__right_ = APLOps.exec(new APLOps.floor(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__fb_;
      _T__right_ = APLOps.exec(new APLOps.floor(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__fc_;
      _T__right_ = APLOps.exec(new APLOps.floor(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.head(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.head(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.head(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.shape(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
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
      _T__right_ = new APLTensor(10.0);
      _T__right_ = APLOps.exec(new APLOps.indices(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.ravel(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.ravel(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.ravel(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.pi(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.pi(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.pi(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.ln(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.ln(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.ln(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.reverse(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.reverse(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.reverse(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.reverse2(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.reverse2(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.reverse2(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.trans(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.trans(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.trans(), _T__right_);
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
      _T__right_ = _V__a_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.factorial(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__b_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.factorial(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__c_;
      _T__right_ = APLOps.exec(new APLOps.abs(), _T__right_);
      _T__right_ = APLOps.exec(new APLOps.factorial(), _T__right_);
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
      _T__right_ = _V__fa_;
      _T__right_ = APLOps.exec(new APLOps.round(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__fb_;
      _T__right_ = APLOps.exec(new APLOps.round(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__fc_;
      _T__right_ = APLOps.exec(new APLOps.round(), _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
   }
}