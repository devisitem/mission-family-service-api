package me.missionfamily.web.mission_family_be.common.exception;

public class ServiceException  extends RuntimeException{

    HttpResponseStatus status;

     public ServiceException(HttpResponseStatus status){
         super(status.getMessage());
         this.status = status;
     }

     public HttpResponseStatus getStatus(){
         return this.status;
     }

     public int getResultCode(){
         return status.getCode();
     }
     public String getMessage(){
         return status.getMessage();
     }
}
