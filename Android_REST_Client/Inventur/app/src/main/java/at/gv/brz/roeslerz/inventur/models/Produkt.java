package at.gv.brz.roeslerz.inventur.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Produkt  implements Parcelable {

    public static final Creator<Produkt> CREATOR = new Creator<Produkt>() {
        @Override
        public Produkt createFromParcel(Parcel in) {
            return new Produkt(in);
        }

        @Override
        public Produkt[] newArray(int size) {
            return new Produkt[size];
        }
    };

    @SerializedName("pid")
    private int pid;

    @SerializedName("lid")
    private int lid;

    @SerializedName("produktId")
    private String produktId;

    @SerializedName("produktName")
    private String produktName;

    @SerializedName("produktPreis")
    private int produktPreis;

    @SerializedName("produktAnzahl")
    private int produktAnzahl;

    public Produkt(int pid, int lid, String produktId, String produktName, int produktPreis, int produktAnzahl) {
        this.pid = pid;
        this.lid = lid;
        this.produktId = produktId;
        this.produktName = produktName;
        this.produktPreis = produktPreis;
        this.produktAnzahl = produktAnzahl;
    }

    public Produkt(Parcel in) {
        pid = in.readInt();
        lid = in.readInt();
        produktId = in.readString();
        produktName = in.readString();
        produktPreis = in.readInt();
        produktAnzahl = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pid);
        dest.writeInt(lid);
        dest.writeString(produktId);
        dest.writeString(produktName);
        dest.writeInt(produktPreis);
        dest.writeInt(produktAnzahl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getPid() { return pid; }

    public int getLid() {
        return lid;
    }

    public String getProduktId() {
        return produktId;
    }

    public String getProduktName() {
        return produktName;
    }

    public int getProduktPreis() {
        return produktPreis;
    }

    public int getProduktAnzahl() {
        return produktAnzahl;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "pid=" + pid +
                ", lid=" + lid +
                ", produktId='" + produktId + '\'' +
                ", produktName='" + produktName + '\'' +
                ", produktPreis=" + produktPreis +
                ", produktAnzahl=" + produktAnzahl +
                '}';
    }
}
