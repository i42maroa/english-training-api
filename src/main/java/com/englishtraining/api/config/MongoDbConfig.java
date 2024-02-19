package com.englishtraining.api.config;

import com.englishtraining.api.config.converters.BigDecimalToDecimal128Converter;
import com.englishtraining.api.config.converters.DateToOffsetDateTimeConverter;
import com.englishtraining.api.config.converters.Decimal128ToBigDecimalConverter;
import com.englishtraining.api.config.converters.OffsetDateTimeToDateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;

@Configuration
public class MongoDbConfig {

    @Bean
    public MongoCustomConversions customConversions(){
        var converters = new ArrayList<Converter<?, ?>>();
        converters.add(new OffsetDateTimeToDateConverter());
        converters.add(new DateToOffsetDateTimeConverter());
        converters.add(new Decimal128ToBigDecimalConverter());
        converters.add(new BigDecimalToDecimal128Converter());
        return new MongoCustomConversions(converters);
    }
}
