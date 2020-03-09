package com.example.fragmentapps;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelHero implements Parcelable {

    private String heroName, heroDesc;
    private int heroImage;

    public ModelHero() { }

    protected ModelHero(Parcel in) {
        heroName = in.readString();
        heroDesc = in.readString();
        heroImage = in.readInt();
    }

    public static final Creator<ModelHero> CREATOR = new Creator<ModelHero>() {
        @Override
        public ModelHero createFromParcel(Parcel in) {
            return new ModelHero(in);
        }

        @Override
        public ModelHero[] newArray(int size) {
            return new ModelHero[size];
        }
    };

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroDesc() {
        return heroDesc;
    }

    public void setHeroDesc(String heroDesc) {
        this.heroDesc = heroDesc;
    }

    public int getHeroImage() {
        return heroImage;
    }

    public void setHeroImage(int heroImage) {
        this.heroImage = heroImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(heroName);
        dest.writeString(heroDesc);
        dest.writeInt(heroImage);
    }
}
