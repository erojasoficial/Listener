package com.example.demo.converter;

import com.example.demo.constants.IndexConstants;
import com.example.demo.domain.model.StringPosition;
import com.example.demo.exceptions.StringConversionException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;

@Component
public class StringToStringPositionConverter {
    public StringPosition[] convert(String input) {
        try {
            if (input == null) {
                input = "";
            }

            StringPosition[] result = new StringPosition[IndexConstants.INDICES.length];
            for (int i = 0; i < IndexConstants.INDICES.length; i++) {
                int start = IndexConstants.INDICES[i][0];
                int end = IndexConstants.INDICES[i][1];
                end = Math.min(end, input.length());
                String substr = input.substring(start, end);
                if (end - start > 0) {
                    substr = String.format("%-" + (end - start) + "s", substr);
                }
                result[i] = new StringPosition(substr, i);
            }
            return result;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Error in string indices during string conversion: " + e.getMessage());
        } catch (Exception e) {
            throw new StringConversionException("An unexpected error occurred during string conversion: " + e.getMessage(), e);
        }
    }
}


