// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.

package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;


import frc.robot.RobotContainer;
import frc.robot.subsystems.NavX;




// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.Drivetrain;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class HaloDrive extends CommandBase {

NavX navx = new NavX();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
        private final Drivetrain m_drivetrain;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS


    public HaloDrive(Drivetrain subsystem) {


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        m_drivetrain = subsystem;
        addRequirements(m_drivetrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.

    private void drive(){
        
        double leftSpeed =RobotContainer.getInstance().getdriverJoystick().getRawAxis(5);
        double rightSpeed =RobotContainer.getInstance().getdriverJoystick().getRawAxis(1);
        
        //making deadband
        double deadband = 0.3;
        if(Math.abs(leftSpeed) < deadband)
        {
            leftSpeed = 0;
        }

        if(Math.abs(rightSpeed) < deadband)
        {
            rightSpeed = 0;
        }
        
        
        // delinearization. change exponent to increase/ decrease power
        int exponent = 3;
        leftSpeed = Math.pow(leftSpeed, exponent);
        rightSpeed = Math.pow(rightSpeed, exponent);

        double finesse = 1;
        if (RobotContainer.getInstance().getdriverJoystick().getXButton()){finesse = .5;}

        leftSpeed *=  finesse;
        rightSpeed *= finesse;


        m_drivetrain.driveTank(leftSpeed, rightSpeed);

        SmartDashboard.putNumber("leftSpeed", rightSpeed);
        SmartDashboard.putNumber("rightSpeed", leftSpeed);
    }



    public void levelSelf()
    {
        navx.displayAxis();
//finds the pitch of the robot
double pitch = (double)navx.getPitch();
SmartDashboard.putNumber("pitch", pitch);

//the steeper the slope, the more power we put into the drive.
//20 is a random value and 0.083 is the minimum input required 
//to move the wheels on a flat surface

double speed = (pitch/20)+0.083;
SmartDashboard.putNumber("speed1", speed);
SmartDashboard.putNumber("speed2", speed);


m_drivetrain.driveTank(speed, speed);
    }


    @Override
    public void execute() {

    SmartDashboard.putBoolean("self level activated", RobotContainer.getInstance().getdriverJoystick().getRightBumper());
if(RobotContainer.getInstance().getdriverJoystick().getRightBumper())
{
    levelSelf();
}
else{
    drive();
}
        

        SmartDashboard.putNumber("drive train voltage", m_drivetrain.getDrivetrainVoltage());
        SmartDashboard.putNumber("can bus voltage", m_drivetrain.getMotorControllerVoltage());
        SmartDashboard.putNumber("voltage used", m_drivetrain.getDrivetrainVoltage()*m_drivetrain.getMotorControllerVoltage());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
