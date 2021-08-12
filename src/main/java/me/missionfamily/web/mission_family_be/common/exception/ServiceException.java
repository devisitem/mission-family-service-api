package me.missionfamily.web.mission_family_be.common.exception;

import me.missionfamily.web.mission_family_be.common.HttpResponseStatus;

public class ServiceException  extends RuntimeException{

    HttpResponseStatus status;

     public ServiceException(HttpResponseStatus status){
         super(status.getMessage());
         this.status = status;
     }

     public HttpResponseStatus getStatus(){
         return this.status;
     }
}
