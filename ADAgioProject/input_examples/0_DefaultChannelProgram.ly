\version "2.16.2"
\score {
 <<
\new Staff{
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 

\set midiMinimumVolume = #0
\set midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<a cis' e'>2\mf <a c' e'>2 
<ais cisis' eis'>2 <ais cis' eis'>2 
<aes c' ees'>2 <aes ces' ees'>2 
}
\new Staff{
s4 s4 s4 s4 
s4 s4 s4 s4 

\set midiMinimumVolume = #0
\set midiMaximumVolume = #0.7
\set Staff.midiInstrument = #"violin"
<b dis' fis'>4\mf <b d' fis'>4 <bis disis' fisis'>4 s4 
<bis dis' fisis'>4 <bes d' f'>4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
<a cis' e'>2 <a c' e'>2 
<ais cisis' eis'>2 <ais cis' eis'>2 
<aes c' ees'>2 <aes ces' ees'>2 
}
\new Staff{
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 

\set midiMinimumVolume = #0
\set midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<a cis' e'>2\mf <a c' e'>2 
<ais cisis' eis'>2 <ais cis' eis'>2 
<aes c' ees'>2 <aes ces' ees'>2 
}
\new Staff{

\tempo 4=90
\clef treble
\time 4/4
\set midiMinimumVolume = #0
\set midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<a cis' e'>4\mf <a c' e'>4 <ais cisis' eis'>4 <ais cis' eis'>4 
<aes c' ees'>2 <aes ces' ees'>2 
s4 s4 s4 s4 
s4 s4 s4 s4 
<a cis' e'>2 <a c' e'>2 
<ais cisis' eis'>2 <ais cis' eis'>2 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
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