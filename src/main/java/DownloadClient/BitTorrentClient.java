package DownloadClient;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;
import com.turn.ttorrent.client.peer.SharingPeer;

public class BitTorrentClient {

	public static void main(String[] args) {

		try {			
			// First, instantiate the Client object.
			Client client = new Client(
			// This is the interface the client will listen on (you might need something
			// else than localhost here).
			InetAddress.getLocalHost(),

			// Load the torrent from the torrent file and use the given
			// output directory. Partials downloads are automatically recovered.
			SharedTorrent.fromFile(
			new File("./clientDownload/Black Label XXL.torrent"),
			new File("./clientDownloadFiles")));

			// You can optionally set download/upload rate limits
			// in kB/second. Setting a limit to 0.0 disables rate
			// limits.
//			client.setMaxDownloadRate(5000000.0);
//			client.setMaxUploadRate(500000.0);

			// At this point, can you either call download() to download the torrent and
			// stop immediately after...
			client.download();
			long a = System.currentTimeMillis();
			
			client.addObserver(new Observer() {
				  public void update(Observable observable, Object data) {
				    Client client = (Client) observable;
				    float progress = client.getTorrent().getCompletion();
				    System.out.println(progress + "%");
				    // Do something with progress.
				  }
				});
			
//			URL whatismyip = new URL("http://checkip.amazonaws.com");
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//			                whatismyip.openStream()));
//
//			String ip = in.readLine(); //you get the IP as a String
//			System.out.println(ip);
//			System.out.println(InetAddress.getByName(ip));

			
			//new InetSocketAddress("201.244.249.81", 0);

			// Or call client.share(...) with a seed time in seconds:
			//client.share(3600);
			// Which would seed the torrent for an hour after the download is complete.

			// Downloading and seeding is done in background threads.
			// To wait for this process to finish, call:
			//client.waitForCompletion();
			System.out.println(System.currentTimeMillis()-a);
			
			// At any time you can call client.stop() to interrupt the download.
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}


