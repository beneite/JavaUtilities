package docker.practiseImages.passingParameterToContainer;

public class ParameterToContainerProgram{
	public static void main(String[] args){
		int num = Integer.parseInt(args[0]);
		System.out.println("The number is :"+num);


		for(int i=1;i <= num ;i++){
			System.out.println("Square of "+i+" is:"+(i*i));
		}
	}
}