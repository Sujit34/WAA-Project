package edu.miu.WAA_Project.controller.exception_controller;

import edu.miu.WAA_Project.entity.dto.response.ErrorResponse;
import edu.miu.WAA_Project.entity.dto.response.ValidationErrorResponse;
import edu.miu.WAA_Project.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AuthExceptionController {
    @ExceptionHandler(value = ErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse exception(ErrorException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        String errorMessages = errors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(";"));
        return new ErrorResponse(errorMessages);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> exceptionValidationHandler(ConstraintViolationException exception) {
        List<String> errorMessages = new ArrayList<ConstraintViolation>(exception.getConstraintViolations()).stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(errorMessages);
        return ResponseEntity.status(400).body(validationErrorResponse);
    }

    @ExceptionHandler(value = UserNotVerifiedException.class)
    public ResponseEntity<ErrorResponse> exceptionUserNotVerifiedExceptionHandler(UserNotVerifiedException exception) {

        ErrorResponse errorResponse = new ErrorResponse("User is not verified.");
        return ResponseEntity.status(400).body(errorResponse);
    }


    @ExceptionHandler(value = UserNotExistsException.class)
    public ResponseEntity<ErrorResponse> exceptionUserNotExistsExceptionHandler(UserNotExistsException exception) {

        ErrorResponse errorResponse = new ErrorResponse("User doesnt exists.");
        return ResponseEntity.status(400).body(errorResponse);
    }

    public ResponseEntity<ErrorResponse> exceptionUserDeactivatedExceptionHandler(UserDeactivatedException exception) {
        ErrorResponse errorResponse = new ErrorResponse("The Account is disabled.");
        return ResponseEntity.status(401).body(errorResponse);
    }

    @ExceptionHandler(value = UserIsLockedException.class)
    public ResponseEntity<ErrorResponse> exceptionUserIsLockedExceptionHandler(UserIsLockedException exception) {

        ErrorResponse errorResponse = new ErrorResponse("User is locked.");
        return ResponseEntity.status(400).body(errorResponse);
    }
    @ExceptionHandler(value = UserInvalidLoginAttemptCount.class)
    public ResponseEntity<ErrorResponse> exceptionUserInvalidLoginAttemptCountHandler(UserInvalidLoginAttemptCount exception) {

        ErrorResponse errorResponse = new ErrorResponse("Number of invalid attempt is exited, need to wait 15 minutes ");
        return ResponseEntity.status(400).body(errorResponse);
    }
    @ExceptionHandler(value = UserNotActiveException.class)
    public ResponseEntity<ErrorResponse> exceptionUserNotActiveExceptionHandler(UserNotActiveException exception) {

        ErrorResponse errorResponse = new ErrorResponse("User is not active. ");
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(value = UserLockedTimeException.class)
    public ResponseEntity<ErrorResponse> exceptionUserLockedTimeExceptionHandler(UserLockedTimeException exception) {

        ErrorResponse errorResponse = new ErrorResponse("User is locked for 15 minutes.");
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(value = ActionPermissionDeniedException.class)
    public ResponseEntity<ErrorResponse> exceptionActionDeniedExceptionHandler(ActionPermissionDeniedException exception) {

        ErrorResponse errorResponse = new ErrorResponse("Action permission denied.");
        return ResponseEntity.status(400).body(errorResponse);
    }
    @ExceptionHandler(value = InvalidCredentialException.class)
    public ResponseEntity<ErrorResponse> exceptionInvalidCredentialExceptionHandler(InvalidCredentialException exception) {

        ErrorResponse errorResponse = new ErrorResponse("Invalid Credential.");
        return ResponseEntity.status(400).body(errorResponse);
    }

}