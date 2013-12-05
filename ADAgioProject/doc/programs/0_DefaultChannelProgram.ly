\version "2.16.2"
\score {
 <<
\new Staff {
\clef treble
\time 4/4
\set Staff.midiInstrument = #"acoustic grand"
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0

s1 s1 s1 s1 s1 s1 s1 s1 s1 s1 s1 s1 s1 s1 s1 s1 s1 s1 
<a cis' e'>1\mf <a c' e'>1 <ais cisis' eis'>1 <ais cis' eis'>1 <aes c' ees'>1 <aes ces' ees'>1 

}
\new Staff {
\clef treble
\time 4/4
\set Staff.midiInstrument = #"acoustic grand"
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0

<a cis' e'>1\mf <a c' e'>1 <ais cisis' eis'>1 <ais cis' eis'>1 <aes c' ees'>1 <aes ces' ees'>1 


\clef treble
\time 4/4
\set Staff.midiInstrument = #"acoustic grand"
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0

s1 s1 s1 s1 s1 s1 
<a cis' e'>1 <a c' e'>1 <ais cisis' eis'>1 <ais cis' eis'>1 <aes c' ees'>1 <aes ces' ees'>1 

}
\new Staff {
\clef treble
\time 4/4
\set Staff.midiInstrument = #"violin"
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #0.7

s1 s1 s1 s1 s1 s1 
<b dis' fis'>1\mf <b d' fis'>1 <bis disis' fisis'>1 <bis dis' fisis'>1 <bes d' f'>1 <bes des' f'>1 


\clef treble
\time 4/4
\set Staff.midiInstrument = #"violin"
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #0.7

s1 s1 s1 s1 s1 s1 
<a cis' e'>1 <a c' e'>1 <ais cisis' eis'>1 <ais cis' eis'>1 <aes c' ees'>1 <aes ces' ees'>1 

}
>> 
\layout{ }
\midi {
\context {
\Score 
tempoWholesPerMinute = #(ly:make-moment 72 2)
}
}
}