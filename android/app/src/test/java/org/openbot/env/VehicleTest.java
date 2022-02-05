package org.openbot.env;

import static org.junit.Assert.assertEquals;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openbot.utils.Enums;
import org.openbot.vehicle.Control;
import org.openbot.vehicle.Vehicle;

@RunWith(AndroidJUnit4.class)
public class VehicleTest {

  private Vehicle vehicle;

  @Before
  public void setupVehicle() {
    vehicle = new Vehicle(ApplicationProvider.getApplicationContext(), 115200);
  }

  @Test
  public void getRotation() {
    assertEquals(0, vehicle.getRotation(), 0.01f);

    vehicle.setControl(new Control(0.5f, 1.0f));
    assertEquals(-60, vehicle.getRotation(), 0.01f);

    vehicle.setControl(new Control(0.0f, 1.0f));
    assertEquals(-180, vehicle.getRotation(), 0.01f);
  }

  @Test
  public void getSpeed() {
    vehicle.setSpeedFactor(Enums.SpeedMode.SLOW.getValue());
    vehicle.setControl(new Control(-1.0f, 1.0f));

    assertEquals(-0.33f, vehicle.getLeftSpeed(), 0.01f);
    assertEquals(0.33f, vehicle.getRightSpeed(), 0.01f);

    vehicle.setSpeedFactor(Enums.SpeedMode.NORMAL.getValue());
    vehicle.setControl(new Control(1.0f, -1.0f));

    assertEquals(0.5f, vehicle.getLeftSpeed(), 0.01f);
    assertEquals(-0.5f, vehicle.getRightSpeed(), 0.01f);

    vehicle.setSpeedFactor(Enums.SpeedMode.FAST.getValue());
    vehicle.setControl(new Control(1.0f, 1.0f));

    assertEquals(1.0f, vehicle.getLeftSpeed(), 0.01f);
    assertEquals(1.0f, vehicle.getRightSpeed(), 0.01f);
  }
}
