package kalkulatormechanics;

import java.util.Scanner;

public class Main
	{
		public static void treetest()
			{
//				//tree test
//				binaryTree tree = new binaryTree("+");
//				/*
//				 * 	2+2*2
//				 * 					+
//				 * 			*				2
//				 * 		2		2
//				 * 
//				 *  = 6
//				 */
//				tree.root.left = new Node("*");
//				tree.root.left.left = new Node("2");
//				tree.root.left.right = new Node("2");
//				tree.root.right = new Node("2");
//				tree.calculate();
			}

		public static void test() throws Exception
			{
				String test1 = "( 4 - 2 ) * 2";

				Kalkulator kalkulator1 = new Kalkulator(test1);
				System.out.println("Expected = 4\n" + "Actual = " + kalkulator1.calculate());

				String test2 = "2 ^ 3 ^ 4";
				kalkulator1.eval(test2);
				System.out.println("Expected = E+24\n" + "Actual = " + kalkulator1.calculate());

				String test3 = "( ( 2 + 2 ) % 2 )  + ( ( 2 + 2 ) * 2 )";
				kalkulator1.eval(test3);
				System.out.println("Expected = 8\n" + "Actual = " + kalkulator1.calculate());

				String test4 = "16 ^ 1 / 2";
				kalkulator1.eval(test4);
				System.out.println("Expected = 4\n" + "Actual = " + kalkulator1.calculate());
			}

		public static void main(String[] args) throws Exception
			{

				test();

				Kalkulator kalkulator = new Kalkulator("2 + 2");
				@SuppressWarnings("resource") // nobody cares
				Scanner in = new Scanner(System.in);
				while (true)
				{
					System.out.println();
					System.out.println("podaj wyrazenie oddzielone spacjami np ( 2 + 2 ) * 2");
					String input = in.nextLine();
					if (input.isEmpty())
						break;
					kalkulator.eval(input);
					System.out.println(kalkulator.calculate());

				}
				System.out.println("koniec");

			}

	}
