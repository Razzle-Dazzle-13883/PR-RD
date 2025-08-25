package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;

public class LConstants {
    static {
        // TicksToInches: The number of inches per ticks of the encoder for forward movement Default Value: 1
        ThreeWheelConstants.forwardTicksToInches = 0.00057495117;
        ThreeWheelConstants.strafeTicksToInches = 0.00057495117;
        ThreeWheelConstants.turnTicksToInches = 0.00057495117;
        ThreeWheelConstants.leftY = 3.25;
        ThreeWheelConstants.rightY = -3.25;
        ThreeWheelConstants.strafeX = 3.25;
        ThreeWheelConstants.leftEncoder_HardwareMapName = "rightRear";
        ThreeWheelConstants.rightEncoder_HardwareMapName = "leftFront";
        ThreeWheelConstants.strafeEncoder_HardwareMapName = "rightFront";
        ThreeWheelConstants.leftEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.rightEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.strafeEncoderDirection = Encoder.REVERSE;
    }
}




