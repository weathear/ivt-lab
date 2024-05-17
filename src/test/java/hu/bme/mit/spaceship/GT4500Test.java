package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mocpr;
  private TorpedoStore mocsec;

  @BeforeEach
  public void init(){
    mocpr = mock(TorpedoStore.class);
    mocsec = mock(TorpedoStore.class);
    this.ship = new GT4500(mocpr, mocsec);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mocpr.isEmpty()).thenReturn(false);
    when(mocpr.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mocpr, times(1)).isEmpty();
    verify(mocpr, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mocpr.isEmpty()).thenReturn(false);
    when(mocpr.fire(1)).thenReturn(true);
    when(mocsec.isEmpty()).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mocpr, times(1)).isEmpty();
    verify(mocpr, times(1)).fire(1);
    verify(mocsec, times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_Single_Twice_Success(){
    // Arrange
    when(mocpr.isEmpty()).thenReturn(false);
    when(mocpr.fire(1)).thenReturn(true);
    when(mocsec.isEmpty()).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, result2);
    verify(mocpr, times(2)).isEmpty();
    verify(mocpr, times(2)).fire(1);
    verify(mocsec, times(1)).isEmpty();
    verify(mocsec, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Fail(){
    // Arrange
    when(mocpr.isEmpty()).thenReturn(false);
    when(mocpr.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mocpr, times(1)).isEmpty();
    verify(mocpr, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Twice_Alternate_Success(){
    // Arrange
    when(mocpr.isEmpty()).thenReturn(false);
    when(mocpr.fire(1)).thenReturn(true);
    when(mocsec.isEmpty()).thenReturn(false);
    when(mocsec.fire(1)).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, result2);
    verify(mocpr, times(1)).isEmpty();
    verify(mocpr, times(1)).fire(1);
    verify(mocsec, times(1)).isEmpty();
    verify(mocsec, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Fail(){
    // Arrange
    when(mocpr.isEmpty()).thenReturn(true);
    when(mocsec.isEmpty()).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mocpr, times(1)).isEmpty();
    verify(mocpr, times(0)).fire(1);
    verify(mocsec, times(1)).isEmpty();
    verify(mocsec, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Both_Success(){
    // Arrange
    when(mocpr.isEmpty()).thenReturn(false);
    when(mocpr.fire(1)).thenReturn(true);
    when(mocsec.isEmpty()).thenReturn(false);
    when(mocsec.fire(1)).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mocpr, times(1)).isEmpty();
    verify(mocpr, times(1)).fire(1);
    verify(mocsec, times(1)).isEmpty();
    verify(mocsec, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Twice_Fail(){
    // Arrange
    when(mocpr.isEmpty()).thenReturn(false);
    when(mocpr.fire(1)).thenReturn(true);
    when(mocsec.isEmpty()).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    when(mocpr.isEmpty()).thenReturn(true);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(false, result2);
    verify(mocpr, times(2)).isEmpty();
    verify(mocpr, times(1)).fire(1);
    verify(mocsec, times(1)).isEmpty();
    verify(mocsec, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Secondary_Single_Twice_Success(){
    // Arrange
    when(mocsec.isEmpty()).thenReturn(false);
    when(mocsec.fire(1)).thenReturn(true);
    when(mocpr.isEmpty()).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, result2);
    verify(mocsec, times(2)).isEmpty();
    verify(mocsec, times(2)).fire(1);
    verify(mocpr, times(2)).isEmpty();
    verify(mocpr, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Secondary_Single_Twice_Fail(){
    // Arrange
    when(mocsec.isEmpty()).thenReturn(false);
    when(mocsec.fire(1)).thenReturn(true);
    when(mocpr.isEmpty()).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    when(mocsec.isEmpty()).thenReturn(true);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(false, result2);
    verify(mocsec, times(2)).isEmpty();
    verify(mocsec, times(1)).fire(1);
    verify(mocpr, times(2)).isEmpty();
    verify(mocpr, times(0)).fire(1);
  }
}
