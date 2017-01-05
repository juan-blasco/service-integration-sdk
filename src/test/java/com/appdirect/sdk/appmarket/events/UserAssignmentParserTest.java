package com.appdirect.sdk.appmarket.events;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserAssignmentParserTest {
	private UserAssignmentParser testedEventParser;

	@Before
	public void setUp() throws Exception {
		testedEventParser = new UserAssignmentParser();
	}

	@Test
	public void testParse_whenParsingAnEventInfoInstanceOfTypeUserAssign_generateTheAppropriateRichEventObject() throws Exception {
		//Given
		String expectedAccountId = "expectedAccountId";
		String expectedAssignedUserId = "expectedAssignedUserId";
		String expectedConsumerKey = "expectedConsumerKey";
		HashMap<String, String[]> expectedQueryParams = new HashMap<>();
		EventFlag expectedEventFlag = null;
		UserAssignment expectedRichEvent = new UserAssignment(expectedAssignedUserId, expectedAccountId, expectedConsumerKey, expectedQueryParams, expectedEventFlag);

		EventInfo testEventInfo = userAssignmentEvent(expectedAccountId, expectedAssignedUserId);
		EventHandlingContext testEventHandlingContext = new EventHandlingContext(expectedConsumerKey, expectedQueryParams);


		//When
		UserAssignment parsedRichEvent = testedEventParser.parse(testEventInfo, testEventHandlingContext);

		//Then
		assertThat(parsedRichEvent).isEqualTo(expectedRichEvent);
	}

	private EventInfo userAssignmentEvent(String accountIdentifier, String userIdentifier) {
		return EventInfo.builder()
			.type(EventType.USER_ASSIGNMENT)
			.payload(EventPayload.builder()
				.account(AccountInfo.builder()
					.accountIdentifier(accountIdentifier)
					.build())
				.user(UserInfo.builder()
					.uuid(userIdentifier)
					.build())
				.build())
			.build();
	}
}