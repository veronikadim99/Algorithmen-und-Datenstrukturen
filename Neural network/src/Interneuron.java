import java.util.ArrayList;

/**
 * The class Neuron implents a interneuron for the class Network.
 * 
 * @author Vera Röhr
 * @version 1.0
 * @since 2019-01-11
 */
public class Interneuron extends Neuron {
	/**
	 * {@inheritDoc}
	 */
	public Interneuron(int index) {
		// TODO
		super(index);  // making a constructor for index
		this.outgoingsynapses = new ArrayList<>();

	}

	/**
	 * Divides incoming signal into equal parts for all the outgoing synapses
	 * 
	 * @param input 3 dimensional signal from another neuron
	 * @return 3 dimensional neural signal (after processing)Adds neurons to the network.
	 */
	@Override
	public double[] integrateSignal(double[] signal) {
		// TODO
		int sumOfSynapses = outgoingsynapses.size();  //integer for the number of synapses
		double[] arrayDevided = new double[signal.length];  //creating a new Array for the devided signal
		for(int i = 0; i < signal.length; i++) {
			arrayDevided[i] = signal[i] / sumOfSynapses;  // deviding the incoming signal into equal parts for all the outgoing synapses


		}

		for(int i = 0; i < outgoingsynapses.size() ; i++) {
			Synapse everySynapse = outgoingsynapses.get(i); //get() to get something from the array list
			everySynapse.transmit(arrayDevided);

		}
		signal = arrayDevided;

		return signal;
	}
}

