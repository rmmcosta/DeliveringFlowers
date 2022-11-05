package com.rmmcosta.deliveringflowers.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Plant not found")
public class PlantNotFoundException extends RuntimeException {

}
