package com.example.vinhntph08047_lab4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootModel {

    @SerializedName("photos")
    @Expose
    private Photos photos;

    public RootModel(Photos photos) {
        this.photos = photos;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public class Photos {
        @SerializedName("photo")
        @Expose
        private List<Photo> photo = null;

        public List<Photo> getPhoto() {
            return photo;
        }

        public void setPhoto(List<Photo> photo) {
            this.photo = photo;
        }

        public class Photo {
            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("owner")
            @Expose
            private String owner;
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("url_sq")
            @Expose
            private String urlSq;
            @SerializedName("height_sq")
            @Expose
            private Integer heightSq;
            @SerializedName("width_sq")
            @Expose
            private Integer widthSq;
            @SerializedName("url_t")
            @Expose
            private String urlT;
            @SerializedName("height_t")
            @Expose
            private Integer heightT;
            @SerializedName("width_t")
            @Expose
            private Integer widthT;
            @SerializedName("url_s")
            @Expose
            private String urlS;
            @SerializedName("height_s")
            @Expose
            private Integer heightS;
            @SerializedName("width_s")
            @Expose
            private Integer widthS;
            @SerializedName("url_q")
            @Expose
            private String urlQ;
            @SerializedName("height_q")
            @Expose
            private Integer heightQ;
            @SerializedName("width_q")
            @Expose
            private Integer widthQ;
            @SerializedName("url_m")
            @Expose
            private String urlM;
            @SerializedName("height_m")
            @Expose
            private Integer heightM;
            @SerializedName("width_m")
            @Expose
            private Integer widthM;
            @SerializedName("url_n")
            @Expose
            private String urlN;
            @SerializedName("height_n")
            @Expose
            private Integer heightN;
            @SerializedName("width_n")
            @Expose
            private Integer widthN;
            @SerializedName("url_z")
            @Expose
            private String urlZ;
            @SerializedName("height_z")
            @Expose
            private Integer heightZ;
            @SerializedName("width_z")
            @Expose
            private Integer widthZ;
            @SerializedName("url_c")
            @Expose
            private String urlC;
            @SerializedName("height_c")
            @Expose
            private Integer heightC;
            @SerializedName("width_c")
            @Expose
            private Integer widthC;
            @SerializedName("url_l")
            @Expose
            private String urlL;
            @SerializedName("height_l")
            @Expose
            private Integer heightL;
            @SerializedName("width_l")
            @Expose
            private Integer widthL;
            @SerializedName("url_o")
            @Expose
            private String urlO;
            @SerializedName("height_o")
            @Expose
            private Integer heightO;
            @SerializedName("width_o")
            @Expose
            private Integer widthO;

            public Photo(String id, String owner, String title, String urlSq, Integer heightSq, Integer widthSq, String urlT, Integer heightT, Integer widthT, String urlS, Integer heightS, Integer widthS, String urlQ, Integer heightQ, Integer widthQ, String urlM, Integer heightM, Integer widthM, String urlN, Integer heightN, Integer widthN, String urlZ, Integer heightZ, Integer widthZ, String urlC, Integer heightC, Integer widthC, String urlL, Integer heightL, Integer widthL, String urlO, Integer heightO, Integer widthO) {
                this.id = id;
                this.owner = owner;
                this.title = title;
                this.urlSq = urlSq;
                this.heightSq = heightSq;
                this.widthSq = widthSq;
                this.urlT = urlT;
                this.heightT = heightT;
                this.widthT = widthT;
                this.urlS = urlS;
                this.heightS = heightS;
                this.widthS = widthS;
                this.urlQ = urlQ;
                this.heightQ = heightQ;
                this.widthQ = widthQ;
                this.urlM = urlM;
                this.heightM = heightM;
                this.widthM = widthM;
                this.urlN = urlN;
                this.heightN = heightN;
                this.widthN = widthN;
                this.urlZ = urlZ;
                this.heightZ = heightZ;
                this.widthZ = widthZ;
                this.urlC = urlC;
                this.heightC = heightC;
                this.widthC = widthC;
                this.urlL = urlL;
                this.heightL = heightL;
                this.widthL = widthL;
                this.urlO = urlO;
                this.heightO = heightO;
                this.widthO = widthO;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrlSq() {
                return urlSq;
            }

            public void setUrlSq(String urlSq) {
                this.urlSq = urlSq;
            }

            public Integer getHeightSq() {
                return heightSq;
            }

            public void setHeightSq(Integer heightSq) {
                this.heightSq = heightSq;
            }

            public Integer getWidthSq() {
                return widthSq;
            }

            public void setWidthSq(Integer widthSq) {
                this.widthSq = widthSq;
            }

            public String getUrlT() {
                return urlT;
            }

            public void setUrlT(String urlT) {
                this.urlT = urlT;
            }

            public Integer getHeightT() {
                return heightT;
            }

            public void setHeightT(Integer heightT) {
                this.heightT = heightT;
            }

            public Integer getWidthT() {
                return widthT;
            }

            public void setWidthT(Integer widthT) {
                this.widthT = widthT;
            }

            public String getUrlS() {
                return urlS;
            }

            public void setUrlS(String urlS) {
                this.urlS = urlS;
            }

            public Integer getHeightS() {
                return heightS;
            }

            public void setHeightS(Integer heightS) {
                this.heightS = heightS;
            }

            public Integer getWidthS() {
                return widthS;
            }

            public void setWidthS(Integer widthS) {
                this.widthS = widthS;
            }

            public String getUrlQ() {
                return urlQ;
            }

            public void setUrlQ(String urlQ) {
                this.urlQ = urlQ;
            }

            public Integer getHeightQ() {
                return heightQ;
            }

            public void setHeightQ(Integer heightQ) {
                this.heightQ = heightQ;
            }

            public Integer getWidthQ() {
                return widthQ;
            }

            public void setWidthQ(Integer widthQ) {
                this.widthQ = widthQ;
            }

            public String getUrlM() {
                return urlM;
            }

            public void setUrlM(String urlM) {
                this.urlM = urlM;
            }

            public Integer getHeightM() {
                return heightM;
            }

            public void setHeightM(Integer heightM) {
                this.heightM = heightM;
            }

            public Integer getWidthM() {
                return widthM;
            }

            public void setWidthM(Integer widthM) {
                this.widthM = widthM;
            }

            public String getUrlN() {
                return urlN;
            }

            public void setUrlN(String urlN) {
                this.urlN = urlN;
            }

            public Integer getHeightN() {
                return heightN;
            }

            public void setHeightN(Integer heightN) {
                this.heightN = heightN;
            }

            public Integer getWidthN() {
                return widthN;
            }

            public void setWidthN(Integer widthN) {
                this.widthN = widthN;
            }

            public String getUrlZ() {
                return urlZ;
            }

            public void setUrlZ(String urlZ) {
                this.urlZ = urlZ;
            }

            public Integer getHeightZ() {
                return heightZ;
            }

            public void setHeightZ(Integer heightZ) {
                this.heightZ = heightZ;
            }

            public Integer getWidthZ() {
                return widthZ;
            }

            public void setWidthZ(Integer widthZ) {
                this.widthZ = widthZ;
            }

            public String getUrlC() {
                return urlC;
            }

            public void setUrlC(String urlC) {
                this.urlC = urlC;
            }

            public Integer getHeightC() {
                return heightC;
            }

            public void setHeightC(Integer heightC) {
                this.heightC = heightC;
            }

            public Integer getWidthC() {
                return widthC;
            }

            public void setWidthC(Integer widthC) {
                this.widthC = widthC;
            }

            public String getUrlL() {
                return urlL;
            }

            public void setUrlL(String urlL) {
                this.urlL = urlL;
            }

            public Integer getHeightL() {
                return heightL;
            }

            public void setHeightL(Integer heightL) {
                this.heightL = heightL;
            }

            public Integer getWidthL() {
                return widthL;
            }

            public void setWidthL(Integer widthL) {
                this.widthL = widthL;
            }

            public String getUrlO() {
                return urlO;
            }

            public void setUrlO(String urlO) {
                this.urlO = urlO;
            }

            public Integer getHeightO() {
                return heightO;
            }

            public void setHeightO(Integer heightO) {
                this.heightO = heightO;
            }

            public Integer getWidthO() {
                return widthO;
            }

            public void setWidthO(Integer widthO) {
                this.widthO = widthO;
            }
        }


    }
}
