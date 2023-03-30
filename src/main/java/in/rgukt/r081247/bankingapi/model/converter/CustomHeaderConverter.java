package in.rgukt.r081247.bankingapi.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.rgukt.r081247.bankingapi.exception.BankingException;
import in.rgukt.r081247.bankingapi.model.CustomHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomHeaderConverter implements Converter<String, CustomHeader> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomHeaderConverter.class);

    @Override
    public CustomHeader convert(String s) {
        ObjectMapper om = new ObjectMapper();
        CustomHeader customHeader = null;
        try {
            customHeader = om.readValue(s, CustomHeader.class);
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<CustomHeader>> violations = validator.validate(customHeader);
            if(!violations.isEmpty()) {
                String error = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(". "));
                LOGGER.warn("'customHeader' Header Validation Failed: " + error);
                throw new BankingException("'customHeader' Header Validation Failed");
            }
        } catch (JsonProcessingException e) {
            LOGGER.warn("'customHeader' Header Validation Failed: " + e.getMessage());
            throw new BankingException("'customHeader' Header Validation Failed");
        }
        return customHeader;
    }
}
