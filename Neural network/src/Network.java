/**
 * <NEWLINE>
 * 
 * The Network class implements a neural network.
 * <p>
 * The network consists of three types of neurons: photoreceptors(@see
 * Photoreceptor), interneurons(@see Interneuron) and 6 cortical neurons(@see
 * CorticalNeuron). The network processes light waves. There are three types of
 * photoreceptors, that perceive the different colors.
 * 
 * @author Vera Röhr
 * @version 1.0
 * @since 2019-01-11
 */
public class Network {
	/** #Photoreceptors in the network */
	int receptors;
	/** #Cortical neurons in the network */
	int cortical;
	/** All the neurons in the network */
	Neuron[] neurons;
	/** Different receptor types */
	String[] receptortypes = { "blue", "green", "red" };

	/**
	 * Adds neurons to the network.
	 * <p>
	 * Defines the neurons in the network.
	 * 
	 * @param inter
	 *            #Interneurons
	 * @param receptors
	 *            #Photoreceptors
	 * @param cortical
	 *            #CorticalNeurons
	 */
	public Network(int inter, int receptors, int cortical) {
		// TODO

		int sumOfNeurons = inter + receptors + cortical;//sum of all of the neurons

		if (receptors < 3) {
			throw new RuntimeException("Less Receptors than 3."); //throw new exception if less than 3 photoreceptors
		}


		if(receptors > inter){
			throw new RuntimeException("Less Interneuronen than Photoreceptors."); //throw new exception if the number of inter is less than the number of photoreceptors
		}

		this.cortical = cortical; //reference to the cortical
		this.receptors = receptors;  //reference to the receptors

		neurons = new Neuron[sumOfNeurons];

		int n = 0; // creating a variable


		if (receptors % 3 != 0) {
			for (int colors = 0; colors < receptors / 3 + 1; colors++) {
				neurons[n] = new Photoreceptor(n, receptortypes[0]); //if the rest different than 0; then add 1 to blue
				n++;
			}
			if (receptors % 3 == 2) {
				for (int colors = 0; colors < receptors / 3 + 1; colors++) {
					neurons[n] = new Photoreceptor(n, receptortypes[1]);
					//if the rest is two, then add 1 to green

					n++;
				}
			} else {
				for (int i = 0; i < receptors / 3; i++) {
					neurons[n] = new Photoreceptor(n, receptortypes[1]); //sort the green receptors, if the rest is 1
					n++;
				}

			}
			for (int i = 0; i < receptors / 3; i++) {
				neurons[n] = new Photoreceptor(n, receptortypes[2]); //if there is rest,sort also the red receptors
				n++;
			}
		} else {

			for (int colors = 0; colors < receptortypes.length; colors++) {
				for (int i = 0; i < receptors / 3; i++) {
					//sorting the receptors; they are first in the Netzwerk
					neurons[n] = new Photoreceptor(n, receptortypes[colors]);
					n++;
				}
			}
		}

        for (int i = 0; i < inter; i++) {
            neurons[n] = new Interneuron(n); //sorting the interneurons; they are second in the Netzwerk
            n++;
        }


        for (int i = 0; i < cortical; i++) {
			neurons[n] = new CorticalNeuron(n);  //sorting the Corticalss; they are the last ones in the Netzwerk
			n++;
		}


	}

	/**
	 * Add a Synapse between the Neurons. The different neurons have their outgoing
	 * synapses as an attribute. ({@link Interneuron}, {@link Photoreceptor},
	 * {@link CorticalNeuron})
	 * 
	 * @param n1
	 *            Presynaptic Neuron (Sender)
	 * @param n2
	 *            Postsynaptic Neuron (Receiver)
	 */

	public void addSynapse(Neuron n1, Neuron n2) {
		// TODO

		Neuron presynaptic = neurons[n1.index]; //finding the index of the presynaptic neuron
		Neuron postsynaptic = neurons[n2.index]; //finding the index of the possynaptic neuron

		Synapse synapse1 = new Synapse(presynaptic, postsynaptic);  //creating the new synapse

		presynaptic.addSynapse(synapse1); //adding the synapse after the presynaptic neuron


	}

	/**
	 * Processes the light waves. The lightwaves are integrated be the
	 * photoreceptors (@see Photoreceptor.integrateSignal(double[] signal)) and the
	 * final neural signal is found by summing up the signals in the cortical
	 * neurons(@see CorticalNeuron)
	 * 
	 * @param input
	 *            light waves
	 * @return the neural signal that can be used to classify the color
	 */
	public double[] signalprocessing(double[] input) {
		// TODO
		double signal[];
        double[] colors;
		//int interNeurons = neurons.length - (cortical + receptors); //defining the size of the interneurons
        int corticalNeurons = this.neurons.length - cortical; // defining the size of the corticals
		double[] endSignal = new double[3];  //new signal
        for (int j = 0; j < receptors; j++) {

			neurons[j].integrateSignal(input);  //integrate the signal from the photoreceptors and it becomes input

		}


		for (int j = corticalNeurons; j < neurons.length; j++) {
			for (int i = 0; i < 3; i++) {



				endSignal[i] +=((CorticalNeuron) neurons[j]).getSignal()[i];
				//integrating the signal and then we are adding all of the integrated signals to the main signal
			}
		}

		signal = endSignal; //the signal = to the main signal
		colors = countColorreceptors();
        for(int i = 0; i < 3; i++) {
            signal[i] = signal[i] / colors[i]; //dividing the signal  by the number of receptor types
		}



		return signal; //returning the signal
	}

	public double[] countColorreceptors() {
        double[] colorreceptors = new double[3];
        Photoreceptor c;

        for (Neuron neuron : this.neurons) {
            if (neuron instanceof Photoreceptor) {
                c = (Photoreceptor) neuron;
                if (c.type == "blue")
                    colorreceptors[0]++;
                else if (c.type == "green")
                    colorreceptors[1]++;
                else if (c.type == "red")
                    colorreceptors[2]++;
            }

        }
        return colorreceptors;

    }

	/**
	 * Classifies the neural signal to a color.
	 * 
	 * @param signal
	 *            neural signal from the cortical neurons
	 * @return color of the mixed light signals as a String
	 */
	public String colors(double[] signal) {
		String color = "grey";
		if (signal[0] > 0.6 && signal[1] < 0.074)
			color = "violet";
		else if (signal[0] > 0.21569 && signal[1] < 0.677)
			color = "blue";
		else if (signal[0] <= 0.21569 && signal[1] > 0.677 && signal[2] > 0.333)
			color = "green";
		else if (signal[1] < 0.713 && signal[2] > 0.913)
			color = "yellow";
		else if (signal[1] > 0.068 && signal[2] > 0.227)
			color = "orange";
		else if (signal[2] > 0.002)
			color = "red";
		return color;
	}

	public static void main(String[] args) {

	}
}