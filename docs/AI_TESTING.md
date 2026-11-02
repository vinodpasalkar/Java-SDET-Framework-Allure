# AI-assisted software testing

## Use cases
- Draft scenarios from requirements.
- Suggest boundary and negative cases.
- Explain stack traces and group similar failures.
- Generate synthetic data without real personal information.
- Summarize test reports and identify coverage gaps.
- Suggest API payload variations from a schema.

## Risks and controls
- Hallucinations: trace suggestions to approved requirements.
- Data leakage: use approved services and sanitized inputs.
- Nondeterminism: validate structure and measurable properties, not exact prose.
- Prompt injection: treat app content and retrieved documents as untrusted.
- Bias: evaluate representative datasets.
- False confidence: review AI-generated tests as engineering artefacts.

## Interview answer
“I use AI as an accelerator, not an oracle. It can propose scenarios, data and failure explanations, but I validate suggestions against requirements. For AI-enabled features I test relevance, groundedness, robustness, safety, latency and cost using a versioned evaluation dataset and human-reviewed criteria.”
