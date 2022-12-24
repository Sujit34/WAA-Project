package edu.miu.WAA_Project.aspect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.miu.WAA_Project.entity.Logs;
import edu.miu.WAA_Project.repository.LogsRepo;
import edu.miu.WAA_Project.service.LogsService;
import edu.miu.WAA_Project.service.UserService;
import edu.miu.WAA_Project.service.impl.ApplicationUserDetail;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Map;

@Aspect
@Component

public class LoggerAspect {


    final HttpServletRequest request ;

    final LogsService logsService;
    final LogsRepo logRepo;
    ApplicationUserDetail applicationUserDetail;

    private final UserService userService;

    public LoggerAspect(HttpServletRequest request, LogsService logsService, LogsRepo logRepo, ApplicationUserDetail applicationUserDetail, UserService userService) {
        this.request = request;
        this.logsService = logsService;
        this.logRepo = logRepo;
        this.applicationUserDetail = applicationUserDetail;
        this.userService = userService;
    }


    @Pointcut("within(edu.miu.WAA_Project.controller.*)")
    public void logging(){

    }




    @Around("logging()")

    public Object doLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String username=  SecurityContextHolder.getContext().getAuthentication().getName();

        long start = System.nanoTime();


        String requestURI = request.getRequestURI();
        String requestMethod=request.getMethod();
        Map<String, String[]> requestParameters =request.getParameterMap();
        Object requestBody =proceedingJoinPoint.getArgs();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JsonElement jsonRequestParameters = gson.toJsonTree(requestParameters);
//        JsonElement jsonRequestBody = gson.toJsonTree(requestBody);


        var result = proceedingJoinPoint.proceed();

        long finish = System.nanoTime();

        JsonObject logObject = new JsonObject();
        logObject.addProperty("url", requestURI);
        logObject.addProperty("method", requestMethod);
        logObject.add("getparam", jsonRequestParameters);
//        logObject.add("body", jsonRequestBody);
//        logObject.add("response", gson.toJson(result.getClass().getSimpleName()) );

        Logs activityLog = new Logs();

        activityLog.setCreatedDate(LocalDateTime.now());
        activityLog.setUserName(username);
        activityLog.setLogMessage(logObject.toString());
        activityLog.setIsDeleted(false);
        logRepo.save(activityLog);

        return  result;

    }

}
