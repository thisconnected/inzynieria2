package operacje;

import java.util.List;

public class Modulo extends Operation
	{
		@Override
		protected void init()
			{
				// System.out.println("constructs modulo");
				operation = "%";
				priority = 2;
				alignment = true;
				argumentCount = 2;
				// System.out.println(this);
			}

		@Override
		public double doOperation(List<Double> lista)
			{
				return lista.get(1) % lista.get(0); // div#0
			}
	}
