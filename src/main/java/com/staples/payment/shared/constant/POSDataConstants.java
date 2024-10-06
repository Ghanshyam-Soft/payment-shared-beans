package com.staples.payment.shared.constant;

public class POSDataConstants
{
	public enum CardCaptureSource // Characters [0, 2]
	{
		INT, // Internet
		PHN, // Phone
		MAL, // Mail
		FAX, // Fax
		TER; // Terminal
	};

	public enum PinCaptureCapable // Characters [3,5]
	{
		// also used for CVV/CCIN
		PIN, // capable
		PNC; // not capable
	};

	public enum InputMethod // Characters [6,8]
	{
		SWP, // swipe
		MAN, // manual
		RFD, // rfd
		CSC, // contactless
		DIP, // chip
		WLT;// wallet
	}

	public enum CardPresent // Characters [9,11]
	{
		CRP, // card present
		CPE, // card present, emv
		CNP, // card not present
		CNR;// card not required
	}

	public enum AuthorizationMode // Characters [12,14]
	{
		ONL, // online
		OFL; // offline

		// TODO: May be more values. They will come from our work on the bridge from contract.
	}
}
