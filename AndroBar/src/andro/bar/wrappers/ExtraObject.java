package andro.bar.wrappers;

import android.os.Parcel;
import android.os.Parcelable;

public class ExtraObject implements Parcelable{

    public Object[] Obj;
    
    public ExtraObject(Object[] obj){
        Obj = obj;
    }

     public int describeContents() {
         return 0;
     }

     public void writeToParcel(Parcel out, int flags) {
         out.writeArray(Obj);
     }

     public static final Parcelable.Creator<ExtraObject> CREATOR
             = new Parcelable.Creator<ExtraObject>() {
         public ExtraObject createFromParcel(Parcel in) {
             return new ExtraObject(in);
         }

         public ExtraObject[] newArray(int size) {
             return new ExtraObject[size];
         }
     };
     
     private ExtraObject(Parcel in) {
         Obj = in.readArray(new ClassLoader(ClassLoader.getSystemClassLoader()) {});
     }
}
