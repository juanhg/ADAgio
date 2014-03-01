\version "2.16.2"
\score {
 <<
\new Staff{
s1
s1
s1
s1
s1
s1
s1
s1
s1

\tempo 4=60
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #0.8
\set Staff.midiInstrument = #"acoustic grand"
<< { a2\mf } \\ { s2 c'4\mf } \\ { s2 e'4\mf } \\ { s2. a4\mf } \\ { s1 } \\ >>
<< { ais2 } \\ { s2 cis'4 } \\ { s2 eis'4 } \\ { s2. ais4 } \\ { s1 } \\ >>
<< { aes2 } \\ { s2 ces'4 } \\ { s2 ees'4 } \\ { s2. aes4 } \\ { s1 } \\ >>

}
\new Staff{
s1
s1
s1

\tempo 4=120
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #0.7
\set Staff.midiInstrument = #"violin"
<< { b2\mf } \\ { s2 d'4\mf } \\ { s2 fis'4\mf } \\ { s2. b4\mf } \\ { s1 } \\ >>
<< { bis2 } \\ { s2 dis'4 } \\ { s2 fisis'4 } \\ { s2. bis4 } \\ { s1 } \\ >>
<< { bes2 } \\ { s2 des'4 } \\ { s2 f'4 } \\ { s2. bes4 } \\ { s1 } \\ >>
s1
s1
s1

\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #0.6
<< { a2\mf } \\ { s2 c'4\mf } \\ { s2 e'4\mf } \\ { s2. a4\mf } \\ { s1 } \\ >>
<< { ais2 } \\ { s2 cis'4 } \\ { s2 eis'4 } \\ { s2. ais4 } \\ { s1 } \\ >>
<< { aes2 } \\ { s2 ces'4 } \\ { s2 ees'4 } \\ { s2. aes4 } \\ { s1 } \\ >>

}
\new Staff{

\tempo 4=70
\clef treble
\time 4/4
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<< { a2\mf } \\ { s2 c'4\mf } \\ { s2 e'4\mf } \\ { s2. a4\mf } \\ { s1 } \\ >>
<< { ais2 } \\ { s2 cis'4 } \\ { s2 eis'4 } \\ { s2. ais4 } \\ { s1 } \\ >>
<< { aes2 } \\ { s2 ces'4 } \\ { s2 ees'4 } \\ { s2. aes4 } \\ { s1 } \\ >>
s1
s1
s1

\tempo 4=70
<< { a2 } \\ { s2 c'4 } \\ { s2 e'4 } \\ { s2. a4 } \\ { s1 } \\ >>
<< { ais2 } \\ { s2 cis'4 } \\ { s2 eis'4 } \\ { s2. ais4 } \\ { s1 } \\ >>
<< { aes2 } \\ { s2 ces'4 } \\ { s2 ees'4 } \\ { s2. aes4 } \\ { s1 } \\ >>
s1
s1
s1

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