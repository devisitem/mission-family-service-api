package me.missionfamily.web.mission_family_be.common.exception;

public class ServiceException  extends RuntimeException{

    MissionStatus status;

     public ServiceException(MissionStatus status){
         super(status.getMessage());
         this.status = status;
     }

     public MissionStatus getStatus(){
         return this.status;
     }

     public int getResultCode(){
         return status.getCode();
     }
     public String getMessage(){
         return status.getMessage();
     }
}
