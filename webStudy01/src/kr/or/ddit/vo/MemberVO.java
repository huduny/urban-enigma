package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * member테이블을 대상으로 한 회원관리용 domain layer
 * @author PC22
 *
 */
public class MemberVO implements Serializable {
   
   public MemberVO() {
      super();
   }
   
   
   
   private MemberVO(String memId, String memPass, String memName, String memRegno1, String memRegno2, String memBir,
         String memZip, String memAdd1, String memAdd2, String memHometel, String memComtel, String memHp,
         String memMail, String memJob, String memLike, String memMemorial, String memMemorialday,
         Integer memMileage, String memDelete) {
      super();
      this.memId = memId;
      this.memPass = memPass;
      this.memName = memName;
      this.memRegno1 = memRegno1;
      this.memRegno2 = memRegno2;
      this.memBir = memBir;
      this.memZip = memZip;
      this.memAdd1 = memAdd1;
      this.memAdd2 = memAdd2;
      this.memHometel = memHometel;
      this.memComtel = memComtel;
      this.memHp = memHp;
      this.memMail = memMail;
      this.memJob = memJob;
      this.memLike = memLike;
      this.memMemorial = memMemorial;
      this.memMemorialday = memMemorialday;
      this.memMileage = memMileage;
      this.memDelete = memDelete;
   }



   private String memId;
   private transient String memPass;
   private String memName;
   private transient String memRegno1;
   private transient String memRegno2;
   private String memBir;
   private String memZip;
   private String memAdd1;
   private String memAdd2;
   private String memHometel;
   private String memComtel;
   private String memHp;
   private String memMail;
   private String memJob;
   private String memLike;
   private String memMemorial;
   private String memMemorialday;
   private Integer memMileage;
   private String memDelete;
   public String getMemId() {
      return memId;
   }
   public void setMemId(String memId) {
      this.memId = memId;
   }
   public String getMemPass() {
      return memPass;
   }
   public void setMemPass(String memPass) {
      this.memPass = memPass;
   }
   public String getMemName() {
      return memName;
   }
   public void setMemName(String memName) {
      this.memName = memName;
   }
   public String getMemRegno1() {
      return memRegno1;
   }
   public void setMemRegno1(String memRegno1) {
      this.memRegno1 = memRegno1;
   }
   public String getMemRegno2() {
      return memRegno2;
   }
   public void setMemRegno2(String memRegno2) {
      this.memRegno2 = memRegno2;
   }
   public String getMemBir() {
      return memBir;
   }
   public void setMemBir(String memBir) {
      this.memBir = memBir;
   }
   public String getMemZip() {
      return memZip;
   }
   public void setMemZip(String memZip) {
      this.memZip = memZip;
   }
   public String getMemAdd1() {
      return memAdd1;
   }
   public void setMemAdd1(String memAdd1) {
      this.memAdd1 = memAdd1;
   }
   public String getMemAdd2() {
      return memAdd2;
   }
   public void setMemAdd2(String memAdd2) {
      this.memAdd2 = memAdd2;
   }
   public String getMemHometel() {
      return memHometel;
   }
   public void setMemHometel(String memHometel) {
      this.memHometel = memHometel;
   }
   public String getMemComtel() {
      return memComtel;
   }
   public void setMemComtel(String memComtel) {
      this.memComtel = memComtel;
   }
   public String getMemHp() {
      return memHp;
   }
   public void setMemHp(String memHp) {
      this.memHp = memHp;
   }
   public String getMemMail() {
      return memMail;
   }
   public void setMemMail(String memMail) {
      this.memMail = memMail;
   }
   public String getMemJob() {
      return memJob;
   }
   public void setMemJob(String memJob) {
      this.memJob = memJob;
   }
   public String getMemLike() {
      return memLike;
   }
   public void setMemLike(String memLike) {
      this.memLike = memLike;
   }
   public String getMemMemorial() {
      return memMemorial;
   }
   public void setMemMemorial(String memMemorial) {
      this.memMemorial = memMemorial;
   }
   public String getMemMemorialday() {
      return memMemorialday;
   }
   public void setMemMemorialday(String memMemorialday) {
      this.memMemorialday = memMemorialday;
   }
   public Integer getMemMileage() {
      return memMileage;
   }
   public void setMemMileage(Integer memMileage) {
      this.memMileage = memMileage;
   }
   public String getMemDelete() {
      return memDelete;
   }
   public void setMemDelete(String memDelete) {
      this.memDelete = memDelete;
   }
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((memId == null) ? 0 : memId.hashCode());
      return result;
   }
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      MemberVO other = (MemberVO) obj;
      if (memId == null) {
         if (other.memId != null)
            return false;
      } else if (!memId.equals(other.memId))
         return false;
      return true;
   }
   @Override
   public String toString() {
      return "MemberVO [memId=" + memId + ", memName=" + memName + ", memBir=" + memBir + ", memZip=" + memZip
            + ", memAdd1=" + memAdd1 + ", memAdd2=" + memAdd2 + ", memHometel=" + memHometel + ", memComtel="
            + memComtel + ", memHp=" + memHp + ", memMail=" + memMail + ", memJob=" + memJob + ", memLike="
            + memLike + ", memMemorial=" + memMemorial + ", memMemorialday=" + memMemorialday + ", memMileage="
            + memMileage + ", memDelete=" + memDelete + "]";
   }
   
   public static MemberVOBuilder builder() {
      return new MemberVOBuilder();
   }
   public static class MemberVOBuilder{
      public MemberVOBuilder() {
         super();
      }
      private String memId;
      private transient String memPass;
      private String memName;
      private transient String memRegno1;
      private transient String memRegno2;
      private String memBir;
      private String memZip;
      private String memAdd1;
      private String memAdd2;
      private String memHometel;
      private String memComtel;
      private String memHp;
      private String memMail;
      private String memJob;
      private String memLike;
      private String memMemorial;
      private String memMemorialday;
      private Integer memMileage;
      private String memDelete;
      
      public MemberVOBuilder memId(String memId) {
         this.memId = memId;
         return this;
      }
      
      public MemberVOBuilder memPass(String memPass) {
         this.memPass = memPass;
         return this;
      }

      public MemberVOBuilder memName(String memName) {
         this.memName = memName;
         return this;
      }

      public MemberVOBuilder memRegno1(String memRegno1) {
         this.memRegno1 = memRegno1;
         return this;
      }

      public MemberVOBuilder memRegno2(String memRegno2) {
         this.memRegno2 = memRegno2;
         return this;
      }

      public MemberVOBuilder memBir(String memBir) {
         this.memBir = memBir;
         return this;
      }

      public MemberVOBuilder memZip(String memZip) {
         this.memZip = memZip;
         return this;
      }

      public MemberVOBuilder memAdd1(String memAdd1) {
         this.memAdd1 = memAdd1;
         return this;
      }

      public MemberVOBuilder memAdd2(String memAdd2) {
         this.memAdd2 = memAdd2;
         return this;
      }

      public MemberVOBuilder memHometel(String memHometel) {
         this.memHometel = memHometel;
         return this;
      }

      public MemberVOBuilder memComtel(String memComtel) {
         this.memComtel = memComtel;
         return this;
      }

      public MemberVOBuilder memHp(String memHp) {
         this.memHp = memHp;
         return this;
      }

      public MemberVOBuilder memMail(String memMail) {
         this.memMail = memMail;
         return this;
      }

      public MemberVOBuilder memJob(String memJob) {
         this.memJob = memJob;
         return this;
      }

      public MemberVOBuilder memLike(String memLike) {
         this.memLike = memLike;
         return this;
      }

      public MemberVOBuilder memMemorial(String memMemorial) {
         this.memMemorial = memMemorial;
         return this;
      }

      public MemberVOBuilder memMemorialday(String memMemorialday) {
         this.memMemorialday = memMemorialday;
         return this;
      }

      public MemberVOBuilder memMileage(Integer memMileage) {
         this.memMileage = memMileage;
         return this;
      }

      public MemberVOBuilder memDelete(String memDelete) {
         this.memDelete = memDelete;
         return this;
      }
      
      
      public MemberVO build() {
         return new MemberVO(memId, memPass, memName, memRegno1, 
               memRegno2, memBir, memZip, memAdd1, memAdd2, 
               memHometel, memComtel, memHp, memMail, memJob, memLike,
               memMemorial, memMemorialday, memMileage, memDelete);
      }
   }
}



















