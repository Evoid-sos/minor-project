package com.example.evoid

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_new_guidelines.*
import java.util.*


class newGuidelines : AppCompatActivity() {
    lateinit var mTTS:TextToSpeech
    var flag=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_guidelines)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'>GUIDELINES </font>")

        fun TxtToSpeechEnglish()
        {
            mTTS= TextToSpeech(applicationContext) { status ->
                if (status != TextToSpeech.ERROR) {
                    mTTS.language = Locale.US
                }
            }
            startSpeak.setOnClickListener {
               val toSpeak = guideText.text.toString()
               if (toSpeak == "")
               {
                   Toast.makeText(this,"Nothing To Speak",Toast.LENGTH_SHORT).show()
               }
               else
                   mTTS.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null)
           }
            stopSpeak.setOnClickListener {
                stopAudio()
            }
        }

        fireGuide.setOnClickListener()
        {
            guideText.text =resources.getString(R.string.fire_guidelines_details)
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            TxtToSpeechEnglish()
            translatetohindi.visibility=View.VISIBLE
            translatetohindi.setOnClickListener {
                    guideText.text =
                        "भवन से बाहर निकलते ही तुरंत आग लगने वाले अलार्म को खींचें। \n \n" +
                                "इमारत को खाली करते समय, उन्हें खोलने से पहले गर्मी के लिए दरवाजे महसूस करना सुनिश्चित करें \n \n" +
                                "अगर हवा में धुआं है तो जमीन के पास रहें। भटकाव को रोकने के लिए दीवार पर एक हाथ रखें। निकटतम निकास के लिए क्रॉल करें \n \n" +
                                "खतरे से दूर होने के बाद, अपने आपातकालीन संपर्क को कॉल करें और उन्हें आग की सूचना दें। किसी सुरक्षित स्थान पर जाए और आपातकालीन कर्मियों से आगे निर्देश के लिए प्रतीक्षा करें"
                translatetohindi.visibility=View.INVISIBLE
                translatetoenglish.visibility=View.VISIBLE
            }
            translatetoenglish.setOnClickListener {
                guideText.text =resources.getString(R.string.fire_guidelines_details)
                translatetohindi.visibility=View.VISIBLE
                translatetoenglish.visibility=View.INVISIBLE
            }
        }
        medicalGuide.setOnClickListener()
        {
            guideText.text=resources.getString(R.string.medic_guidelines_details)
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            translatetohindi.visibility=View.VISIBLE
            translatetohindi.setOnClickListener {
                guideText.text = "घर और काम पर प्राथमिक चिकित्सा किट को संभाल कर रखें \n \n" +
                        "अपने फोन पर एक आपातकालीन ऐप रखें \n \n" +
                        " यदि आपको अस्पताल जाने की आवश्यकता है, तो दवाओं की एक सूची लें, जिसमें यह शामिल है कि दवा कितनी देर तक और कितनी बार ली गई है। \n \n"
                translatetohindi.visibility=View.INVISIBLE
                translatetoenglish.visibility=View.VISIBLE
            }
            translatetoenglish.setOnClickListener {
                guideText.text =resources.getString(R.string.medic_guidelines_details)
                translatetohindi.visibility=View.VISIBLE
                translatetoenglish.visibility=View.INVISIBLE
            }
        }
        accidentGuide.setOnClickListener()
        {
            guideText.text=resources.getString(R.string.accident_guidelines_details)
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            translatetohindi.visibility=View.VISIBLE
            translatetohindi.setOnClickListener {
                guideText.text = "सबसे पहली और महत्वपूर्ण बात आपकी खुद की सुरक्षा है, इससे आप दूसरों की मदद कर पाएंगे और बेहतर स्थिति के साथ पूरी स्थिति का विश्लेषण करने के लिए समय निकाल पाएंगे। \n \n \n" +
                        "एक बार जब आप सुरक्षा पर पहुंच गए हैं, तो अगला कदम यह सुनिश्चित करना है कि दुर्घटना में शामिल सभी लोग सुरक्षित हैं और अपनी और अपनी संभावित चोटों का आकलन करें। \n \n \n" +
                        "एक बार सभी के सुरक्षित हो जाने के बाद, यह जरूरी है कि आप जल्द से जल्द इस दुर्घटना की पुलिस रिपोर्ट दर्ज करें और विस्तृत तरीके से प्रबंधन कर सकें। \n \n \n"
                translatetohindi.visibility=View.INVISIBLE
                translatetoenglish.visibility=View.VISIBLE
            }
            translatetoenglish.setOnClickListener {
                guideText.text =resources.getString(R.string.accident_guidelines_details)
                translatetohindi.visibility=View.VISIBLE
                translatetoenglish.visibility=View.INVISIBLE
            }
        }
        crimeGuide.setOnClickListener()
        {
            guideText.text= resources.getString(R.string.crime_guidelines_details)
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            translatetohindi.visibility=View.VISIBLE
            translatetohindi.setOnClickListener {
                guideText.text = "हर समय अपने परिवेश से अवगत रहें और अपनी प्रवृत्ति पर भरोसा रखें \n \n" +
                        "अपने सिर को सीधा रखकर चलें। आँख से संपर्क करें। चोर अक्सर उन पीड़ितों को निशाना बनाते हैं जो अपने परिवेश पर ध्यान नहीं दे रहे हैं या जो नीचे देख रहे हैं। \n \n \n" +
                        "अपने मार्ग की योजना बनाएं। अच्छी रोशनी वाली सड़कों का इस्तेमाल करें। जंगल, पार्किंग स्थल या गलियों से न जाएं। \n \n"
                translatetohindi.visibility=View.INVISIBLE
                translatetoenglish.visibility=View.VISIBLE
            }
            translatetoenglish.setOnClickListener {
                guideText.text =resources.getString(R.string.crime_guidelines_details)
                translatetohindi.visibility=View.VISIBLE
                translatetoenglish.visibility=View.INVISIBLE
            }
        }
    }

    override fun onPause() {
        if (mTTS.isSpeaking)
        {
            mTTS.stop()
        }
        super.onPause()
    }

    private fun stopAudio()
    {
        if (mTTS.isSpeaking)
        {
            mTTS.stop()
        }
        else
        {
            Toast.makeText(this,"Not Speaking",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
