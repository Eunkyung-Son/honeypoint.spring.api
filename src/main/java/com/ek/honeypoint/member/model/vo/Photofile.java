package com.ek.honeypoint.member.model.vo;

public class Photofile {
		private int photofileId;
		private int rNo;
		private String originFileName;
		private String streFileName;
		private int imgType;
		
		public Photofile() {}
		
		public Photofile(int photofileId, int rNo, String originFileName, String streFileName, int imgType) {
			super();
			this.photofileId = photofileId;
			this.rNo = rNo;
			this.originFileName = originFileName;
			this.streFileName = streFileName;
			this.imgType = imgType;
		}

		public int getPhotofileId() {
			return photofileId;
		}

		public void setPhotofileId(int photofileId) {
			this.photofileId = photofileId;
		}

		public int getrNo() {
			return rNo;
		}

		public void setrNo(int rNo) {
			this.rNo = rNo;
		}

		public String getOriginFileName() {
			return originFileName;
		}

		public void setOriginFileName(String originFileName) {
			this.originFileName = originFileName;
		}

		public String getStreFileName() {
			return streFileName;
		}

		public void setStreFileName(String streFileName) {
			this.streFileName = streFileName;
		}

		public int getImgType() {
			return imgType;
		}

		public void setImgType(int imgType) {
			this.imgType = imgType;
		}

		@Override
		public String toString() {
			return "Photofile [photofileId=" + photofileId + ", rNo=" + rNo + ", originFileName=" + originFileName
					+ ", streFileName=" + streFileName + ", imgType=" + imgType + "]";
		}
		
		
		
}
