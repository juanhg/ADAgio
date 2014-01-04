\version "2.16.2"
\score {
 <<
\new Staff{

\tempo 4=90
\clef treble
\time 3/4
\set midiMinimumVolume = #0
\set midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<a' c'' e''>4\mf <a' c'' e''>4 <a' c'' e''>4 
<a' c'' e''>4 <a' c'' e''>4 <a' c'' e''>4 <a' c'' e''>4 <a' c'' e''>4 s4 
<a' c'' e''>4. <a' c'' e''>4. 

\time 4/4
<b' dis'' fis''>4 <b' d'' fis''>4 <b' dis'' fis''>4 <a' c'' e''>4 
<b' d'' fis''>2 <b' d'' fis''>2 
}
\new Staff{
s4 s4 s4 
s4 s4 s4 s4 s4 s4 
s4 s4 s4 

\set midiMinimumVolume = #0
\set midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<b' dis'' fis''>4\mf <b' d'' fis''>4 <b' dis'' fis''>4 <a' c'' e''>4 
<b' d'' fis''>2 <b' d'' fis''>2 
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