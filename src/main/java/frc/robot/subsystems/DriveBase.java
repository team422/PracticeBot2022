package frc.robot.subsystems;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class DriveBase extends SubsystemBase {
    // A class that creates the Drivebase
    /* Drivebase Diagram
      ___________
    0|-         -|0
     | -       - |
    0|--       --|0
     | -       - |
    0|-_________-|0
    
    */

    // 
    // Declaring the motors for the left side
    public WPI_TalonSRX leftMiddleMaster;
    
    public WPI_VictorSPX leftFrontMotor;
    public WPI_VictorSPX leftBackMotor;
    
    // declaring the motors for the right side
    public WPI_TalonSRX rightMiddleMaster;

    public WPI_VictorSPX rightFrontMotor;
    public WPI_VictorSPX rightBackMotor;

    public SpeedControllerGroup leftMotorGroup;
	public SpeedControllerGroup rightMotorGroup;
	
	// Gyro initializing
	public ADXRS450_Gyro gyroBoi;
	
	// In case declaring the enum doesn't work directly in the gyro contructor:
	// public static final SPI.Port gyroPort = SPI.Port.kOnboardCS0;

    public DriveBase(){
        setName("DriveBase");

        this.leftMiddleMaster = new WPI_TalonSRX(RobotMap.leftMiddleMasterPort);

        this.leftFrontMotor = new WPI_VictorSPX(RobotMap.leftFrontFollower);
        this.leftBackMotor = new WPI_VictorSPX(RobotMap.leftBackFollower);
        
        this.rightMiddleMaster = new WPI_TalonSRX(RobotMap.rightMiddleMasterPort);
        
        this.rightFrontMotor = new WPI_VictorSPX(RobotMap.rightFrontFollower);
        this.rightBackMotor = new WPI_VictorSPX(RobotMap.rightBackFollower);
        
        this.leftMotorGroup = new SpeedControllerGroup(leftMiddleMaster, leftFrontMotor, leftBackMotor);
        this.rightMotorGroup = new SpeedControllerGroup(rightMiddleMaster, rightFrontMotor, rightBackMotor);

		DifferentialDrive tank = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
		
		// Gyro with SPI.Port.kOnboardCS0 being the port enum that is provided by WPILib
		this.gyroBoi = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
		
		// Built in check if the gyro is the correct one and it is connected
		// System.out.println(gyroBoi.isConnected)
		// Might not work, check when working with robot
		gyroBoi.calibrate();
    }

    public void setSpeed(double leftSide, double rightSide){
        leftMotorGroup.set(leftSide);
        rightMotorGroup.set(rightSide);
    }


    public void stopMotors(){
		leftMotorGroup.set(0);
		rightMotorGroup.set(0);
	}
	
	// Add gyro methods

	public void resetGyroAngle(){
		gyroBoi.reset();
	}

	public double getGyroAngle(){
		return gyroBoi.getAngle();
	}

	public 
}
