package org.rm2pt.sample.schoolclass.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.rm2pt.sample.schoolclass.services.DslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalDslParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'SchoolClass'", "'{'", "'members'", "','", "'}'", "'Mentor'", "'Student'", "'('", "')'", "'Teacher'", "'Mentee'"
    };
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=5;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=6;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalDslParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalDslParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalDslParser.tokenNames; }
    public String getGrammarFileName() { return "InternalDsl.g"; }



     	private DslGrammarAccess grammarAccess;

        public InternalDslParser(TokenStream input, DslGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "SchoolClass";
       	}

       	@Override
       	protected DslGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleSchoolClass"
    // InternalDsl.g:64:1: entryRuleSchoolClass returns [EObject current=null] : iv_ruleSchoolClass= ruleSchoolClass EOF ;
    public final EObject entryRuleSchoolClass() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSchoolClass = null;


        try {
            // InternalDsl.g:64:52: (iv_ruleSchoolClass= ruleSchoolClass EOF )
            // InternalDsl.g:65:2: iv_ruleSchoolClass= ruleSchoolClass EOF
            {
             newCompositeNode(grammarAccess.getSchoolClassRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSchoolClass=ruleSchoolClass();

            state._fsp--;

             current =iv_ruleSchoolClass; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSchoolClass"


    // $ANTLR start "ruleSchoolClass"
    // InternalDsl.g:71:1: ruleSchoolClass returns [EObject current=null] : ( () otherlv_1= 'SchoolClass' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'members' otherlv_5= '{' ( (lv_members_6_0= rulePerson ) ) (otherlv_7= ',' ( (lv_members_8_0= rulePerson ) ) )* otherlv_9= '}' )? otherlv_10= '}' ) ;
    public final EObject ruleSchoolClass() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_members_6_0 = null;

        EObject lv_members_8_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:77:2: ( ( () otherlv_1= 'SchoolClass' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'members' otherlv_5= '{' ( (lv_members_6_0= rulePerson ) ) (otherlv_7= ',' ( (lv_members_8_0= rulePerson ) ) )* otherlv_9= '}' )? otherlv_10= '}' ) )
            // InternalDsl.g:78:2: ( () otherlv_1= 'SchoolClass' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'members' otherlv_5= '{' ( (lv_members_6_0= rulePerson ) ) (otherlv_7= ',' ( (lv_members_8_0= rulePerson ) ) )* otherlv_9= '}' )? otherlv_10= '}' )
            {
            // InternalDsl.g:78:2: ( () otherlv_1= 'SchoolClass' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'members' otherlv_5= '{' ( (lv_members_6_0= rulePerson ) ) (otherlv_7= ',' ( (lv_members_8_0= rulePerson ) ) )* otherlv_9= '}' )? otherlv_10= '}' )
            // InternalDsl.g:79:3: () otherlv_1= 'SchoolClass' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'members' otherlv_5= '{' ( (lv_members_6_0= rulePerson ) ) (otherlv_7= ',' ( (lv_members_8_0= rulePerson ) ) )* otherlv_9= '}' )? otherlv_10= '}'
            {
            // InternalDsl.g:79:3: ()
            // InternalDsl.g:80:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSchoolClassAccess().getSchoolClassAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getSchoolClassAccess().getSchoolClassKeyword_1());
            		
            // InternalDsl.g:90:3: ( (lv_name_2_0= ruleEString ) )
            // InternalDsl.g:91:4: (lv_name_2_0= ruleEString )
            {
            // InternalDsl.g:91:4: (lv_name_2_0= ruleEString )
            // InternalDsl.g:92:5: lv_name_2_0= ruleEString
            {

            					newCompositeNode(grammarAccess.getSchoolClassAccess().getNameEStringParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleEString();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSchoolClassRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.rm2pt.sample.schoolclass.Dsl.EString");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,12,FOLLOW_5); 

            			newLeafNode(otherlv_3, grammarAccess.getSchoolClassAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalDsl.g:113:3: (otherlv_4= 'members' otherlv_5= '{' ( (lv_members_6_0= rulePerson ) ) (otherlv_7= ',' ( (lv_members_8_0= rulePerson ) ) )* otherlv_9= '}' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalDsl.g:114:4: otherlv_4= 'members' otherlv_5= '{' ( (lv_members_6_0= rulePerson ) ) (otherlv_7= ',' ( (lv_members_8_0= rulePerson ) ) )* otherlv_9= '}'
                    {
                    otherlv_4=(Token)match(input,13,FOLLOW_4); 

                    				newLeafNode(otherlv_4, grammarAccess.getSchoolClassAccess().getMembersKeyword_4_0());
                    			
                    otherlv_5=(Token)match(input,12,FOLLOW_6); 

                    				newLeafNode(otherlv_5, grammarAccess.getSchoolClassAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalDsl.g:122:4: ( (lv_members_6_0= rulePerson ) )
                    // InternalDsl.g:123:5: (lv_members_6_0= rulePerson )
                    {
                    // InternalDsl.g:123:5: (lv_members_6_0= rulePerson )
                    // InternalDsl.g:124:6: lv_members_6_0= rulePerson
                    {

                    						newCompositeNode(grammarAccess.getSchoolClassAccess().getMembersPersonParserRuleCall_4_2_0());
                    					
                    pushFollow(FOLLOW_7);
                    lv_members_6_0=rulePerson();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getSchoolClassRule());
                    						}
                    						add(
                    							current,
                    							"members",
                    							lv_members_6_0,
                    							"org.rm2pt.sample.schoolclass.Dsl.Person");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalDsl.g:141:4: (otherlv_7= ',' ( (lv_members_8_0= rulePerson ) ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==14) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // InternalDsl.g:142:5: otherlv_7= ',' ( (lv_members_8_0= rulePerson ) )
                    	    {
                    	    otherlv_7=(Token)match(input,14,FOLLOW_6); 

                    	    					newLeafNode(otherlv_7, grammarAccess.getSchoolClassAccess().getCommaKeyword_4_3_0());
                    	    				
                    	    // InternalDsl.g:146:5: ( (lv_members_8_0= rulePerson ) )
                    	    // InternalDsl.g:147:6: (lv_members_8_0= rulePerson )
                    	    {
                    	    // InternalDsl.g:147:6: (lv_members_8_0= rulePerson )
                    	    // InternalDsl.g:148:7: lv_members_8_0= rulePerson
                    	    {

                    	    							newCompositeNode(grammarAccess.getSchoolClassAccess().getMembersPersonParserRuleCall_4_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_7);
                    	    lv_members_8_0=rulePerson();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getSchoolClassRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"members",
                    	    								lv_members_8_0,
                    	    								"org.rm2pt.sample.schoolclass.Dsl.Person");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                    otherlv_9=(Token)match(input,15,FOLLOW_8); 

                    				newLeafNode(otherlv_9, grammarAccess.getSchoolClassAccess().getRightCurlyBracketKeyword_4_4());
                    			

                    }
                    break;

            }

            otherlv_10=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_10, grammarAccess.getSchoolClassAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSchoolClass"


    // $ANTLR start "entryRulePerson"
    // InternalDsl.g:179:1: entryRulePerson returns [EObject current=null] : iv_rulePerson= rulePerson EOF ;
    public final EObject entryRulePerson() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePerson = null;


        try {
            // InternalDsl.g:179:47: (iv_rulePerson= rulePerson EOF )
            // InternalDsl.g:180:2: iv_rulePerson= rulePerson EOF
            {
             newCompositeNode(grammarAccess.getPersonRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePerson=rulePerson();

            state._fsp--;

             current =iv_rulePerson; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePerson"


    // $ANTLR start "rulePerson"
    // InternalDsl.g:186:1: rulePerson returns [EObject current=null] : (this_Mentor_0= ruleMentor | this_Mentee_1= ruleMentee ) ;
    public final EObject rulePerson() throws RecognitionException {
        EObject current = null;

        EObject this_Mentor_0 = null;

        EObject this_Mentee_1 = null;



        	enterRule();

        try {
            // InternalDsl.g:192:2: ( (this_Mentor_0= ruleMentor | this_Mentee_1= ruleMentee ) )
            // InternalDsl.g:193:2: (this_Mentor_0= ruleMentor | this_Mentee_1= ruleMentee )
            {
            // InternalDsl.g:193:2: (this_Mentor_0= ruleMentor | this_Mentee_1= ruleMentee )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==16) ) {
                alt3=1;
            }
            else if ( (LA3_0==21) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalDsl.g:194:3: this_Mentor_0= ruleMentor
                    {

                    			newCompositeNode(grammarAccess.getPersonAccess().getMentorParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Mentor_0=ruleMentor();

                    state._fsp--;


                    			current = this_Mentor_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalDsl.g:203:3: this_Mentee_1= ruleMentee
                    {

                    			newCompositeNode(grammarAccess.getPersonAccess().getMenteeParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Mentee_1=ruleMentee();

                    state._fsp--;


                    			current = this_Mentee_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePerson"


    // $ANTLR start "entryRuleEString"
    // InternalDsl.g:215:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // InternalDsl.g:215:47: (iv_ruleEString= ruleEString EOF )
            // InternalDsl.g:216:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEString"


    // $ANTLR start "ruleEString"
    // InternalDsl.g:222:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;


        	enterRule();

        try {
            // InternalDsl.g:228:2: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // InternalDsl.g:229:2: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // InternalDsl.g:229:2: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_STRING) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_ID) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalDsl.g:230:3: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    			current.merge(this_STRING_0);
                    		

                    			newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalDsl.g:238:3: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FOLLOW_2); 

                    			current.merge(this_ID_1);
                    		

                    			newLeafNode(this_ID_1, grammarAccess.getEStringAccess().getIDTerminalRuleCall_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEString"


    // $ANTLR start "entryRuleMentor"
    // InternalDsl.g:249:1: entryRuleMentor returns [EObject current=null] : iv_ruleMentor= ruleMentor EOF ;
    public final EObject entryRuleMentor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMentor = null;


        try {
            // InternalDsl.g:249:47: (iv_ruleMentor= ruleMentor EOF )
            // InternalDsl.g:250:2: iv_ruleMentor= ruleMentor EOF
            {
             newCompositeNode(grammarAccess.getMentorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMentor=ruleMentor();

            state._fsp--;

             current =iv_ruleMentor; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMentor"


    // $ANTLR start "ruleMentor"
    // InternalDsl.g:256:1: ruleMentor returns [EObject current=null] : ( () otherlv_1= 'Mentor' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')' )? (otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')' )? otherlv_16= '}' ) ;
    public final EObject ruleMentor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:262:2: ( ( () otherlv_1= 'Mentor' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')' )? (otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')' )? otherlv_16= '}' ) )
            // InternalDsl.g:263:2: ( () otherlv_1= 'Mentor' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')' )? (otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')' )? otherlv_16= '}' )
            {
            // InternalDsl.g:263:2: ( () otherlv_1= 'Mentor' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')' )? (otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')' )? otherlv_16= '}' )
            // InternalDsl.g:264:3: () otherlv_1= 'Mentor' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')' )? (otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')' )? otherlv_16= '}'
            {
            // InternalDsl.g:264:3: ()
            // InternalDsl.g:265:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getMentorAccess().getMentorAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,16,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getMentorAccess().getMentorKeyword_1());
            		
            // InternalDsl.g:275:3: ( (lv_name_2_0= ruleEString ) )
            // InternalDsl.g:276:4: (lv_name_2_0= ruleEString )
            {
            // InternalDsl.g:276:4: (lv_name_2_0= ruleEString )
            // InternalDsl.g:277:5: lv_name_2_0= ruleEString
            {

            					newCompositeNode(grammarAccess.getMentorAccess().getNameEStringParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleEString();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMentorRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.rm2pt.sample.schoolclass.Dsl.EString");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,12,FOLLOW_9); 

            			newLeafNode(otherlv_3, grammarAccess.getMentorAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalDsl.g:298:3: (otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==17) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalDsl.g:299:4: otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')'
                    {
                    otherlv_4=(Token)match(input,17,FOLLOW_10); 

                    				newLeafNode(otherlv_4, grammarAccess.getMentorAccess().getStudentKeyword_4_0());
                    			
                    otherlv_5=(Token)match(input,18,FOLLOW_3); 

                    				newLeafNode(otherlv_5, grammarAccess.getMentorAccess().getLeftParenthesisKeyword_4_1());
                    			
                    // InternalDsl.g:307:4: ( ( ruleEString ) )
                    // InternalDsl.g:308:5: ( ruleEString )
                    {
                    // InternalDsl.g:308:5: ( ruleEString )
                    // InternalDsl.g:309:6: ruleEString
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getMentorRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getMentorAccess().getStudentPersonCrossReference_4_2_0());
                    					
                    pushFollow(FOLLOW_11);
                    ruleEString();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalDsl.g:323:4: (otherlv_7= ',' ( ( ruleEString ) ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==14) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalDsl.g:324:5: otherlv_7= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_7=(Token)match(input,14,FOLLOW_3); 

                    	    					newLeafNode(otherlv_7, grammarAccess.getMentorAccess().getCommaKeyword_4_3_0());
                    	    				
                    	    // InternalDsl.g:328:5: ( ( ruleEString ) )
                    	    // InternalDsl.g:329:6: ( ruleEString )
                    	    {
                    	    // InternalDsl.g:329:6: ( ruleEString )
                    	    // InternalDsl.g:330:7: ruleEString
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getMentorRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getMentorAccess().getStudentPersonCrossReference_4_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_11);
                    	    ruleEString();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    otherlv_9=(Token)match(input,19,FOLLOW_12); 

                    				newLeafNode(otherlv_9, grammarAccess.getMentorAccess().getRightParenthesisKeyword_4_4());
                    			

                    }
                    break;

            }

            // InternalDsl.g:350:3: (otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==20) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalDsl.g:351:4: otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')'
                    {
                    otherlv_10=(Token)match(input,20,FOLLOW_10); 

                    				newLeafNode(otherlv_10, grammarAccess.getMentorAccess().getTeacherKeyword_5_0());
                    			
                    otherlv_11=(Token)match(input,18,FOLLOW_3); 

                    				newLeafNode(otherlv_11, grammarAccess.getMentorAccess().getLeftParenthesisKeyword_5_1());
                    			
                    // InternalDsl.g:359:4: ( ( ruleEString ) )
                    // InternalDsl.g:360:5: ( ruleEString )
                    {
                    // InternalDsl.g:360:5: ( ruleEString )
                    // InternalDsl.g:361:6: ruleEString
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getMentorRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getMentorAccess().getTeacherPersonCrossReference_5_2_0());
                    					
                    pushFollow(FOLLOW_11);
                    ruleEString();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalDsl.g:375:4: (otherlv_13= ',' ( ( ruleEString ) ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==14) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalDsl.g:376:5: otherlv_13= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_13=(Token)match(input,14,FOLLOW_3); 

                    	    					newLeafNode(otherlv_13, grammarAccess.getMentorAccess().getCommaKeyword_5_3_0());
                    	    				
                    	    // InternalDsl.g:380:5: ( ( ruleEString ) )
                    	    // InternalDsl.g:381:6: ( ruleEString )
                    	    {
                    	    // InternalDsl.g:381:6: ( ruleEString )
                    	    // InternalDsl.g:382:7: ruleEString
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getMentorRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getMentorAccess().getTeacherPersonCrossReference_5_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_11);
                    	    ruleEString();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    otherlv_15=(Token)match(input,19,FOLLOW_8); 

                    				newLeafNode(otherlv_15, grammarAccess.getMentorAccess().getRightParenthesisKeyword_5_4());
                    			

                    }
                    break;

            }

            otherlv_16=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_16, grammarAccess.getMentorAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMentor"


    // $ANTLR start "entryRuleMentee"
    // InternalDsl.g:410:1: entryRuleMentee returns [EObject current=null] : iv_ruleMentee= ruleMentee EOF ;
    public final EObject entryRuleMentee() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMentee = null;


        try {
            // InternalDsl.g:410:47: (iv_ruleMentee= ruleMentee EOF )
            // InternalDsl.g:411:2: iv_ruleMentee= ruleMentee EOF
            {
             newCompositeNode(grammarAccess.getMenteeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMentee=ruleMentee();

            state._fsp--;

             current =iv_ruleMentee; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMentee"


    // $ANTLR start "ruleMentee"
    // InternalDsl.g:417:1: ruleMentee returns [EObject current=null] : ( () otherlv_1= 'Mentee' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')' )? (otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')' )? otherlv_16= '}' ) ;
    public final EObject ruleMentee() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:423:2: ( ( () otherlv_1= 'Mentee' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')' )? (otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')' )? otherlv_16= '}' ) )
            // InternalDsl.g:424:2: ( () otherlv_1= 'Mentee' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')' )? (otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')' )? otherlv_16= '}' )
            {
            // InternalDsl.g:424:2: ( () otherlv_1= 'Mentee' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')' )? (otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')' )? otherlv_16= '}' )
            // InternalDsl.g:425:3: () otherlv_1= 'Mentee' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')' )? (otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')' )? otherlv_16= '}'
            {
            // InternalDsl.g:425:3: ()
            // InternalDsl.g:426:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getMenteeAccess().getMenteeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,21,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getMenteeAccess().getMenteeKeyword_1());
            		
            // InternalDsl.g:436:3: ( (lv_name_2_0= ruleEString ) )
            // InternalDsl.g:437:4: (lv_name_2_0= ruleEString )
            {
            // InternalDsl.g:437:4: (lv_name_2_0= ruleEString )
            // InternalDsl.g:438:5: lv_name_2_0= ruleEString
            {

            					newCompositeNode(grammarAccess.getMenteeAccess().getNameEStringParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleEString();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMenteeRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.rm2pt.sample.schoolclass.Dsl.EString");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,12,FOLLOW_9); 

            			newLeafNode(otherlv_3, grammarAccess.getMenteeAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalDsl.g:459:3: (otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==17) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalDsl.g:460:4: otherlv_4= 'Student' otherlv_5= '(' ( ( ruleEString ) ) (otherlv_7= ',' ( ( ruleEString ) ) )* otherlv_9= ')'
                    {
                    otherlv_4=(Token)match(input,17,FOLLOW_10); 

                    				newLeafNode(otherlv_4, grammarAccess.getMenteeAccess().getStudentKeyword_4_0());
                    			
                    otherlv_5=(Token)match(input,18,FOLLOW_3); 

                    				newLeafNode(otherlv_5, grammarAccess.getMenteeAccess().getLeftParenthesisKeyword_4_1());
                    			
                    // InternalDsl.g:468:4: ( ( ruleEString ) )
                    // InternalDsl.g:469:5: ( ruleEString )
                    {
                    // InternalDsl.g:469:5: ( ruleEString )
                    // InternalDsl.g:470:6: ruleEString
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getMenteeRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getMenteeAccess().getStudentPersonCrossReference_4_2_0());
                    					
                    pushFollow(FOLLOW_11);
                    ruleEString();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalDsl.g:484:4: (otherlv_7= ',' ( ( ruleEString ) ) )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==14) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // InternalDsl.g:485:5: otherlv_7= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_7=(Token)match(input,14,FOLLOW_3); 

                    	    					newLeafNode(otherlv_7, grammarAccess.getMenteeAccess().getCommaKeyword_4_3_0());
                    	    				
                    	    // InternalDsl.g:489:5: ( ( ruleEString ) )
                    	    // InternalDsl.g:490:6: ( ruleEString )
                    	    {
                    	    // InternalDsl.g:490:6: ( ruleEString )
                    	    // InternalDsl.g:491:7: ruleEString
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getMenteeRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getMenteeAccess().getStudentPersonCrossReference_4_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_11);
                    	    ruleEString();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    otherlv_9=(Token)match(input,19,FOLLOW_12); 

                    				newLeafNode(otherlv_9, grammarAccess.getMenteeAccess().getRightParenthesisKeyword_4_4());
                    			

                    }
                    break;

            }

            // InternalDsl.g:511:3: (otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==20) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalDsl.g:512:4: otherlv_10= 'Teacher' otherlv_11= '(' ( ( ruleEString ) ) (otherlv_13= ',' ( ( ruleEString ) ) )* otherlv_15= ')'
                    {
                    otherlv_10=(Token)match(input,20,FOLLOW_10); 

                    				newLeafNode(otherlv_10, grammarAccess.getMenteeAccess().getTeacherKeyword_5_0());
                    			
                    otherlv_11=(Token)match(input,18,FOLLOW_3); 

                    				newLeafNode(otherlv_11, grammarAccess.getMenteeAccess().getLeftParenthesisKeyword_5_1());
                    			
                    // InternalDsl.g:520:4: ( ( ruleEString ) )
                    // InternalDsl.g:521:5: ( ruleEString )
                    {
                    // InternalDsl.g:521:5: ( ruleEString )
                    // InternalDsl.g:522:6: ruleEString
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getMenteeRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getMenteeAccess().getTeacherPersonCrossReference_5_2_0());
                    					
                    pushFollow(FOLLOW_11);
                    ruleEString();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalDsl.g:536:4: (otherlv_13= ',' ( ( ruleEString ) ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==14) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // InternalDsl.g:537:5: otherlv_13= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_13=(Token)match(input,14,FOLLOW_3); 

                    	    					newLeafNode(otherlv_13, grammarAccess.getMenteeAccess().getCommaKeyword_5_3_0());
                    	    				
                    	    // InternalDsl.g:541:5: ( ( ruleEString ) )
                    	    // InternalDsl.g:542:6: ( ruleEString )
                    	    {
                    	    // InternalDsl.g:542:6: ( ruleEString )
                    	    // InternalDsl.g:543:7: ruleEString
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getMenteeRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getMenteeAccess().getTeacherPersonCrossReference_5_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_11);
                    	    ruleEString();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    otherlv_15=(Token)match(input,19,FOLLOW_8); 

                    				newLeafNode(otherlv_15, grammarAccess.getMenteeAccess().getRightParenthesisKeyword_5_4());
                    			

                    }
                    break;

            }

            otherlv_16=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_16, grammarAccess.getMenteeAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMentee"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000210000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000128000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000084000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000108000L});

}