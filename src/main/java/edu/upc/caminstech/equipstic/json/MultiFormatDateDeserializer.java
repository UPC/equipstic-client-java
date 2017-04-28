package edu.upc.caminstech.equipstic.json;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import edu.upc.caminstech.equipstic.Infraestructura;

/**
 * Classe per deserialitzar dates en diversos formats.
 * <p>
 * Aquesta classe permet deserialitzar dates suportant diversos formats, ja que
 * la API d'EquipsTIC és inconsistent en l'atribut
 * {@link Infraestructura#getDataCarrega()}, ja que el servidor inclou l'hora en
 * alguns casos i en alguns altres no.
 */
public class MultiFormatDateDeserializer extends StdDeserializer<Date> {

    private static final long serialVersionUID = 5935656994568185659L;

    private static final String[] DATE_FORMATS = new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd HH:mm:ss" };

    public MultiFormatDateDeserializer() {
        this(null);
    }

    public MultiFormatDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        final String date = node.textValue();

        try {
            return DateUtils.parseDate(date, DATE_FORMATS);
        } catch (ParseException e) {
            throw new JsonParseException(jp,
                    String.format("Unparseable date '%s'. Supported formats: %s", date, DATE_FORMATS));
        }
    }
}
