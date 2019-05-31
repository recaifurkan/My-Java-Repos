package com.byrfb.objects;

public class Kontenjan implements Comparable<Kontenjan> {
	@Override
	public String toString() {
		return "Kontenjan [universite=" + universite + ", fakulte=" + fakulte + ", brans=" + brans
				+ ", kontenjanSayisi=" + kontenjanSayisi + ", yerlesen=" + yerlesen + ", bosKontenjan=" + bosKontenjan
				+ ", enKucuk=" + enKucuk + ", enBuyuk=" + enBuyuk + "]";
	}
	private String universite;
	private String fakulte;
	private String brans;
	private float kontenjanSayisi;
	private float yerlesen;
	private float bosKontenjan;
	private float enKucuk;
	private float enBuyuk;
	
	public static int sortIndex = 3;
	public String getUniversite() {
		return universite;
	}
	public void setUniversite(String universite) {
		this.universite = universite;
	}
	public String getFakulte() {
		return fakulte;
	}
	public void setFakulte(String fakulte) {
		this.fakulte = fakulte;
	}
	public String getBrans() {
		return brans;
	}
	public void setBrans(String brans) {
		this.brans = brans;
	}
	public float getKontenjanSayisi() {
		return kontenjanSayisi;
	}
	public void setKontenjanSayisi(float kontenjanSayisi) {
		this.kontenjanSayisi = kontenjanSayisi;
	}
	public float getYerlesen() {
		return yerlesen;
	}
	public void setYerlesen(float yerlesen) {
		this.yerlesen = yerlesen;
	}
	public float getBosKontenjan() {
		return bosKontenjan;
	}
	public void setBosKontenjan(float bosKontenjan) {
		this.bosKontenjan = bosKontenjan;
	}
	public float getEnKucuk() {
		return enKucuk;
	}
	public void setEnKucuk(float enKucuk) {
		this.enKucuk = enKucuk;
	}
	public float getEnBuyuk() {
		return enBuyuk;
	}
	public void setEnBuyuk(float enBuyuk) {
		this.enBuyuk = enBuyuk;
	}
	@Override
	public int compareTo(Kontenjan o) {
		switch (sortIndex) {
		case 0:

			return (o.getUniversite().compareToIgnoreCase(this.getUniversite()));

		case 1:

			return Float.compare(o.getEnBuyuk(), this.getEnBuyuk());

		case 2:

			return (o.getBrans().compareToIgnoreCase(this.getBrans()));
					

		default:
			return Float.compare(o.getEnKucuk(), this.getEnKucuk());
			
		}
		// TODO Auto-generated method stub
		
	}
	

}