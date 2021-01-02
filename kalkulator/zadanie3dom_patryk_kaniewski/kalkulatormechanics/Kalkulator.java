package kalkulatormechanics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Kalkulator
	{
		// TODO sane private vs public
		private binaryTree tree;
		static private OperatorManager order;

		private void genHash()
			{
				order = new OperatorManager();
			}

		public Kalkulator(String input) throws Exception
			{
				// TODO stop creating new object and do it properly
				genHash();
				growTree((convert(tokenizer(input))));
				// calculate();
				// calculateBetter();
			}

		public void eval(String input) throws Exception
			{
				growTree((convert(tokenizer(input))));
			}

		private boolean isNumber(String str)
			{
				try
				{
					Double.valueOf(str);
					return true;
				} catch (Exception e)
				{
					return false;
				}
			}

		private void growTree(Queue<String> input)
			{
				// https://eduinf.waw.pl/inf/alg/001_search/0118.php
				// w koncu zrobione logicznie
				System.out.println(input);

				Node temp = null;
				Stack<Node> stack = new Stack<Node>();
				for (String element : input)
				{
					temp = new Node(element);
					if (!isNumber(element))
					{
						for (int i = 0; i < order.getArgumentCount(element); i++) // provision for more arguments
							temp.addChild(stack.pop());
					}
					stack.push(temp);
				}
				tree = new binaryTree(stack.pop());
			}

		private List<String> tokenizer(String input)
			{
				// TODO better tokenization
				List<String> lista;
				lista = Arrays.asList(input.split(" "));

				return lista;
			}

		private void validate(List<String> input) throws Exception
			{
				// TODO input validation
				// throw new Exception("invalid input");
			}

		private Queue<String> convert(List<String> input) throws Exception
			{
				// TODO odrazu na drzewko a nie ONP -> drzewko lolnope
				try
				{
					validate(input);
				} catch (Exception e)
				{
					e.printStackTrace();
					System.exit(-1);
				}

				Queue<String> queue = new LinkedList<>();
				Stack<String> stack = new Stack<>();

				for (String token : input)
				{
					if ("(".equals(token))
					{
						stack.push(token);
						continue;
					}

					if (")".equals(token))
					{
						while (!"(".equals(stack.peek()))
						{
							queue.add(stack.pop());
						}
						stack.pop();
						continue;
					}
					if (order.containsKey(token)) // operator mapped
					{
						while (!stack.empty() && order.get(token) < order.get(stack.peek()))
						{
							if (order.alignment(token) && order.get(token) <= order.get(stack.peek()))
								break;
							queue.add(stack.pop());
						}
						stack.push(token);
						continue;
					}

					if (isNumber(token))
					{
						queue.add(token);
						continue;
					}
				}

				// prepare return
				while (!stack.isEmpty())
				{
					queue.add(stack.pop());
				}

				return queue;
			}

		public double calculate()
			{
				order.printInfix(tree.getRoot());
				System.out.println();
				order.printPostfix(tree.getRoot());
				System.out.println();
				order.printPrefix(tree.getRoot());
				System.out.println();
				return order.calculate(tree.getRoot());
			}

	}
