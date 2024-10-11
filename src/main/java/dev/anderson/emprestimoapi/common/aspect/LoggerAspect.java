package dev.anderson.emprestimoapi.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggerAspect {

    @Around("execution(* dev.anderson.emprestimoapi.client.ClientService.makeClient(..))")
    public Object logMakeClient(ProceedingJoinPoint joinPoint) throws Throwable {
        Object firstParamenter = joinPoint.getArgs()[0];

        log.info("Creating client: {}", firstParamenter);
        return joinPoint.proceed();
    }

    @Around("execution(* dev.anderson.emprestimoapi.client.ClientService.getAllClients(..))")
    public Object logGetAllClients(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Getting all clients");
        return joinPoint.proceed();
    }

    @Around("execution(* dev.anderson.emprestimoapi.client.ClientService.getClientByCpf(..))")
    public Object logGetClientByCpf(ProceedingJoinPoint joinPoint) throws Throwable {
        Object firstParamenter = joinPoint.getArgs()[0];

        log.info("Getting client by CPF: {}", firstParamenter);
        return joinPoint.proceed();
    }

    @Around("execution(* dev.anderson.emprestimoapi.client.ClientService.deleteClientByCpf(..))")
    public Object logDeleteClientByCpf(ProceedingJoinPoint joinPoint) throws Throwable {
        Object firstParamenter = joinPoint.getArgs()[0];

        log.info("Deleting client by CPF: {}", firstParamenter);
        return joinPoint.proceed();
    }

    @Around("execution(* dev.anderson.emprestimoapi.client.ClientService.updateClientByCpf(..))")
    public Object logUpdateClientByCpf(ProceedingJoinPoint joinPoint) throws Throwable {
        Object firstParamenter = joinPoint.getArgs()[0];
        Object secondParamenter = joinPoint.getArgs()[1];

        log.info("Updating client by CPF: {} with data: {}", firstParamenter, secondParamenter);
        return joinPoint.proceed();
    }

    @Around("execution(* dev.anderson.emprestimoapi.loan.LoanService.makeLoan(..))")
    public Object logMakeLoan(ProceedingJoinPoint joinPoint) throws Throwable {
        Object firstParamenter = joinPoint.getArgs()[0];
        Object secondParamenter = joinPoint.getArgs()[1];

        log.info("Creating loan for client with CPF: {} with data: {}", firstParamenter, secondParamenter);
        return joinPoint.proceed();
    }

    @Around("execution(* dev.anderson.emprestimoapi.loan.LoanService.deleteLoan(..))")
    public Object logDeleteLoan(ProceedingJoinPoint joinPoint) throws Throwable {
        Object firstParamenter = joinPoint.getArgs()[0];
        Object secondParamenter = joinPoint.getArgs()[1];

        log.info("Deleting loan for client with CPF: {} and ID: {}", firstParamenter, secondParamenter);
        return joinPoint.proceed();
    }

    @Around("execution(* dev.anderson.emprestimoapi.loan.LoanService.getLoan(..))")
    public Object logGetLoan(ProceedingJoinPoint joinPoint) throws Throwable {
        Object firstParamenter = joinPoint.getArgs()[0];
        Object secondParamenter = joinPoint.getArgs()[1];

        log.info("Getting loan for client with CPF: {} and ID: {}", firstParamenter, secondParamenter);
        return joinPoint.proceed();
    }

    @Around("execution(* dev.anderson.emprestimoapi.loan.LoanService.getAllLoans(..))")
    public Object logGetAllLoans(ProceedingJoinPoint joinPoint) throws Throwable {
        Object firstParamenter = joinPoint.getArgs()[0];

        log.info("Getting all loans for client with CPF: {}", firstParamenter);
        return joinPoint.proceed();
    }

}
