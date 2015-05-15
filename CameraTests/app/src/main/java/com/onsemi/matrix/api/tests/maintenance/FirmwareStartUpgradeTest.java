package com.onsemi.matrix.api.tests.maintenance;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;

import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Authentication;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.onsemi.matrix.api.Settings;
import com.onsemi.matrix.api.Utils;

@RunWith( HttpJUnitRunner.class )
public class FirmwareStartUpgradeTest {
	
	@Rule
	public Destination restfuse = new Destination(this, Settings.getHostname());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;

	@HttpTest(method = Method.GET, path = "vb.htm?firmwareupgrade", 
			authentications = { @Authentication(type = BASIC, user = "admin", password = "admin") }, order = 0)
	public void firmwareupgrade_StartUpgrade_ShouldReturnOK() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK", "response contains 'OK'");
	}
	
	@HttpTest(method = Method.GET, path = "vb.htm?firmwareupgrade=1", 
			authentications = { @Authentication(type = BASIC, user = "admin", password = "admin") }, order = 1)
	public void firmwareupgrade_StartUpgradeWithParameterValue_ShouldReturnNG() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "NG", "response contains 'NG'");
	}
}
