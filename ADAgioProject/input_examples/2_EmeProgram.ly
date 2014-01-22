\version "2.16.2"
\score {
 <<
\new Staff{

\tempo 4=65
\clef treble
\time 4/4
\set midiMinimumVolume = #0
\set midiMaximumVolume = #0.8
\set Staff.midiInstrument = #"acoustic grand"
<c'' e'' g''>2\mf <g' b' d''>2 
<d' f' a'>4 <a c' e'>4 <f a c'>4 s4 
<g b d'>2 <a cis' e'>2 
<c'' e'' g''>2 <g' b' d''>2 
<d' f' a'>4 <a c' e'>4 <f a c'>4 s4 
<g b d'>2 <a c' e'>2 

\tempo 4=70
\set midiMinimumVolume = #0
\set midiMaximumVolume = #1.0
<f a c'>2\mf <c' e' g'>2 
<e gis' b>2 <a c' e'>2 
<f a c'>4 <c' e' g'>4 <g b d'>4 s4 
<f a c'>2 <g b d'>2 

\tempo 4=65
<c'' e'' g''>2 <g' b' d''>2 
<d' f' a'>4 <a c' e'>4 <f a c'>4 s4 
<g b d'>2 <a cis' e'>2 
<c'' e'' g''>2 <g' b' d''>2 
<a' c'' e''>4 <f' a' c''>4 <d' f' a'>4 s4 
<g' b' d''>2 <a' c'' e''>2 

\tempo 4=70
<f' a' c''>2 <c' e' g'>2 
<e' gis' b'>2 <a' c'' e''>2 
<f' a' c''>4 <c' e' g'>4 <g b d'>4 s4 
<f a c'>4 <g b d'>4 <c' e' g'>4 s4 
}
\new Staff{
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 

\set midiMinimumVolume = #0
\set midiMaximumVolume = #0.5
\set Staff.midiInstrument = #"flute"
<f'>2\mf <c'>2 
<e'>2 <a'>2 
<f'>4 <c'>4 <g'>4 s4 
<f'>2 <g'>2 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
s4 s4 s4 s4 
<f'>2 <c'>2 
<e'>2 <a'>2 
<f'>4 <c'>4 <g'>4 s4 
<f'>4 <g'>4 <c'>4 s4 
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