// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

import frc.robot.commands.*;
//import frc.robot.subsystems.*; uncomment when you add a subsystem
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final Gear_Shift m_gear_Shift = new Gear_Shift();
    public final Air_Compressor m_air_Compressor = new Air_Compressor();
    public final Pnuematic_Claw m_pnuematic_Claw = new Pnuematic_Claw();
    public final Drivetrain m_drivetrain = new Drivetrain();

// Joysticks
private final XboxController driverJoystick = new XboxController(1);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems


    // SmartDashboard Buttons
    SmartDashboard.putData("HaloDrive", new HaloDrive( m_drivetrain ));
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    SmartDashboard.putData("Compressor_On", new Compressor_On( m_air_Compressor ));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    m_gear_Shift.setDefaultCommand(new Shift_Gear(false, m_gear_Shift));
    m_air_Compressor.setDefaultCommand(new Compressor_On( m_air_Compressor ));
    m_pnuematic_Claw.setDefaultCommand(new Run_Claw(false, m_pnuematic_Claw));
    m_drivetrain.setDefaultCommand(new HaloDrive( m_drivetrain ));


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
// Create some buttons
final JoystickButton button_CloseClaw = new JoystickButton(driverJoystick, XboxController.Button.kA.value);        
button_CloseClaw.onTrue(new Run_Claw(true, m_pnuematic_Claw).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        SmartDashboard.putData("Button_CloseClaw",new Run_Claw(true, m_pnuematic_Claw));
                        
final JoystickButton button_ShiftGear = new JoystickButton(driverJoystick, XboxController.Button.kY.value);        
button_ShiftGear.toggleOnTrue(new Shift_Gear(false, m_gear_Shift).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        SmartDashboard.putData("Button_ShiftGear",new Shift_Gear(false, m_gear_Shift));
                        
final JoystickButton button_OpenClaw = new JoystickButton(driverJoystick, XboxController.Button.kB.value);        
button_OpenClaw.onTrue(new Run_Claw(false, m_pnuematic_Claw).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        SmartDashboard.putData("Button_OpenClaw",new Run_Claw(false, m_pnuematic_Claw));
                        


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
  }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public XboxController getdriverJoystick() {
      return driverJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }
  

}

