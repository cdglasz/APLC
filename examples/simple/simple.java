import java.util.*;
public class simple {
   private static String sep = "________________________________________________________________________________";
   private static APLTensor _V__x_;
   public static void main(String[] args) {
      APLTensor _T__left_, _T__right_;
      _T__right_ = new APLTensor(1.0, 2.0, 3.0, 4.0, 5.0);
      _V__x_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = _V__x_;
      APLTensor _T__var_0_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_0_;
      _T__right_ = APLOps.exec(new APLOps.mul(), _T__left_, _T__right_);
      _V__x_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(2.0);
      APLTensor _T__var_2_ = _T__right_;
      _T__right_ = new APLTensor(2.0);
      APLTensor _T__var_1_ = _T__right_;
      _T__right_ = _V__x_;
      _T__left_ = _T__right_;
      _T__right_ = _T__var_1_;
      _T__right_ = APLOps.exec(new APLOps.div(), _T__left_, _T__right_);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_2_;
      _T__right_ = APLOps.exec(new APLOps.pow(), _T__left_, _T__right_);
      _V__x_ = _T__right_;
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
      _T__right_ = new APLTensor(6.0, 7.0, 8.0, 9.0, 10.0);
      APLTensor _T__var_5_ = _T__right_;
      _T__right_ = new APLTensor(1.0, 2.0, 3.0, 4.0, 5.0);
      _T__left_ = _T__right_;
      _T__right_ = _T__var_5_;
      _T__right_ = APLOps.exec(new _F__plus_(), _T__left_, _T__right_);
      System.out.println();
      if (_T__right_ == null)
         APLOps.flush();
      else
         System.out.println(_T__right_.toString());
      System.out.println(sep);
   }

   // Function associated with plus
   static class _F__plus_ extends APLOps.Operation {
      public String symbol() { return "plus"; }
      public APLTensor exec(APLTensor _A__left_, APLTensor _A__right_){
         APLTensor _T__left_, _T__right_;
         _T__right_ = _A__right_;
         APLTensor _T__var_3_ = _T__right_;
         _T__right_ = _A__left_;
         _T__left_ = _T__right_;
         _T__right_ = _T__var_3_;
         _T__right_ = APLOps.exec(new APLOps.add(), _T__left_, _T__right_);
         return _T__right_;
      }
   }
}