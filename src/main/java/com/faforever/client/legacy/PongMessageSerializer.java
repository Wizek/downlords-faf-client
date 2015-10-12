package com.faforever.client.legacy;

import com.faforever.client.legacy.io.QDataWriter;
import javafx.beans.property.StringProperty;
import org.springframework.core.serializer.Serializer;

import java.io.IOException;
import java.io.OutputStream;

public class PongMessageSerializer implements Serializer<PongMessage> {

  private final String username;
  private final StringProperty sessionIdProperty;

  /**
   * @param sessionIdProperty the session ID property, so that this serializer can be initialized before the session ID
   * has been set, but it will still get it afterwards.
   */
  public PongMessageSerializer(String username, StringProperty sessionIdProperty) {
    this.username = username;
    this.sessionIdProperty = sessionIdProperty;
  }

  @Override
  public void serialize(PongMessage object, OutputStream outputStream) throws IOException {
    QDataWriter qDataWriter = new QDataWriter(outputStream);
    qDataWriter.append(object.getString());
    qDataWriter.append(username);
    qDataWriter.append(sessionIdProperty.get());
    qDataWriter.flush();
  }
}
